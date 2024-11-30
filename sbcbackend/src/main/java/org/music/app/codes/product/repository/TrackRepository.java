package org.music.app.codes.product.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.product.model.data.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class TrackRepository {
    public static final Logger LOGGER = LogManager.getLogger(TrackRepository.class);

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public boolean addTrack(Track track) {
        try {
            entityManager.persist(track);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public Track findTrackById(Integer trackId) {
        return entityManager.find(Track.class, trackId);
    }

    @Transactional
    public boolean updateTrack(Track updatedTrack) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Track> criteriaUpdate = cb.createCriteriaUpdate(Track.class);
            Root<Track> root = criteriaUpdate.from(Track.class);

            criteriaUpdate.set("trackName", updatedTrack.getTrackName());
            criteriaUpdate.set("trackMusic", updatedTrack.getTrackMusic());
            criteriaUpdate.set("album", updatedTrack.getAlbum());
            criteriaUpdate.set("artist", updatedTrack.getArtist());

            criteriaUpdate.where(cb.equal(root.get("trackId"), updatedTrack.getTrackId()));
            entityManager.createQuery(criteriaUpdate).executeUpdate();
            LOGGER.info("Track updated successfully: {}", updatedTrack.getTrackName());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error updating Track", e);
            return false;
        }
    }

    @Transactional
    public boolean deleteTrack(Integer trackId) {
        try {
            Track track = entityManager.find(Track.class, trackId);
            if (track != null) {
                entityManager.remove(track);
                LOGGER.info("Track deleted successfully with ID: {}", trackId);
                return true;
            }
            LOGGER.warn("No Track found with ID: {}", trackId);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error deleting Track with ID: {}", trackId, e);
            return false;
        }
    }
    
    @Transactional
    public List<Track> getAllTracks() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Track> cq = cb.createQuery(Track.class);
            Root<Track> rootEntry = cq.from(Track.class);
            CriteriaQuery<Track> all = cq.select(rootEntry);
            return entityManager.createQuery(all).getResultList();
        } catch (Exception e) {
            LOGGER.error("Error retrieving all tracks", e);
            return null;
        }
    }

}