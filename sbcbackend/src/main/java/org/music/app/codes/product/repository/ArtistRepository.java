package org.music.app.codes.product.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.product.model.data.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class ArtistRepository {
    public static final Logger LOGGER = LogManager.getLogger(ArtistRepository.class);

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public boolean addArtist(Artist artist) {
        try {
            entityManager.persist(artist);
            LOGGER.info("Artist added successfully: {}", artist.getArtistName());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error adding Artist", e);
            return false;
        }
    }

    @Transactional
    public Artist findArtistById(Integer artistId) {
        try {
            return entityManager.find(Artist.class, artistId);
        } catch (Exception e) {
            LOGGER.error("Error finding Artist with ID: {}", artistId, e);
            return null;
        }
    }

    @Transactional
    public boolean updateArtist(Artist updatedArtist) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Artist> criteriaUpdate = cb.createCriteriaUpdate(Artist.class);
            Root<Artist> root = criteriaUpdate.from(Artist.class);

            criteriaUpdate.set("artistName", updatedArtist.getArtistName());
            criteriaUpdate.set("artistNumber", updatedArtist.getArtistNumber());
            criteriaUpdate.set("artistEmail", updatedArtist.getArtistEmail());
            criteriaUpdate.set("artistLocation", updatedArtist.getArtistLocation());
            criteriaUpdate.where(cb.equal(root.get("artistId"), updatedArtist.getArtistId()));

            entityManager.createQuery(criteriaUpdate).executeUpdate();
            LOGGER.info("Artist updated successfully: {}", updatedArtist.getArtistName());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error updating Artist", e);
            return false;
        }
    }

    @Transactional
    public boolean deleteArtist(Integer artistId) {
        try {
            Artist artist = entityManager.find(Artist.class, artistId);
            if (artist != null) {
                entityManager.remove(artist);
                LOGGER.info("Artist deleted successfully with ID: {}", artistId);
                return true;
            }
            LOGGER.warn("No Artist found with ID: {}", artistId);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error deleting Artist with ID: {}", artistId, e);
            return false;
        }
    }
    
    @Transactional
    public List<Artist> findAllArtists() {
        try {
            return entityManager.createQuery("SELECT a FROM Artist a", Artist.class).getResultList();
        } catch (Exception e) {
            LOGGER.error("Error finding all artists", e);
            return null;
        }
    }
}