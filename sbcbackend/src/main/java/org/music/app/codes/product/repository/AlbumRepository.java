package org.music.app.codes.product.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.product.model.data.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class AlbumRepository {
    public static final Logger LOGGER = LogManager.getLogger(AlbumRepository.class);

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public boolean addAlbum(Album album) {
        try {
            entityManager.persist(album);
            LOGGER.info("Album added successfully: {}", album.getAlbumName());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error adding Album", e);
            return false;
        }
    }

    @Transactional
    public Album findAlbumById(Integer albumId) {
        LOGGER.info("Finding album with ID: {}", albumId);
        Album album = entityManager.find(Album.class, albumId);
        if (album == null) {
            LOGGER.warn("Album with ID: {} not found", albumId);
        } else {
            LOGGER.info("Album with ID: {} found: {}", album.getAlbumName());
        }
        return album;
    }

    @Transactional
    public boolean updateAlbum(Album updatedAlbum) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Album> criteriaUpdate = cb.createCriteriaUpdate(Album.class);
            Root<Album> root = criteriaUpdate.from(Album.class);

            criteriaUpdate.set("albumName", updatedAlbum.getAlbumName());
            criteriaUpdate.set("albumImage", updatedAlbum.getAlbumImage());
            criteriaUpdate.set("albumDesc", updatedAlbum.getAlbumDesc());
            criteriaUpdate.set("albumPrice", updatedAlbum.getAlbumPrice());
            criteriaUpdate.set("artist", updatedAlbum.getArtist());
            criteriaUpdate.set("genre", updatedAlbum.getGenre());

            criteriaUpdate.where(cb.equal(root.get("albumId"), updatedAlbum.getAlbumId()));
            entityManager.createQuery(criteriaUpdate).executeUpdate();
            LOGGER.info("Album updated successfully: {}", updatedAlbum.getAlbumName());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error updating Album", e);
            return false;
        }
    }

    @Transactional
    public boolean deleteAlbum(Integer albumId) {
        try {
            Album album = entityManager.find(Album.class, albumId);
            if (album != null) {
                entityManager.remove(album);
                LOGGER.info("Album deleted successfully with ID: {}", albumId);
                return true;
            }
            LOGGER.warn("No Album found with ID: {}", albumId);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error deleting Album with ID: {}", albumId, e);
            return false;
        }
    }
    
    @Transactional
    public List<Album> searchAlbums(String query) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Album> cq = cb.createQuery(Album.class);
            Root<Album> album = cq.from(Album.class);

            Predicate albumNamePredicate = cb.like(cb.lower(album.get("albumName")), "%" + query.toLowerCase() + "%");
            Predicate artistNamePredicate = cb.like(cb.lower(album.get("artist").get("artistName")), "%" + query.toLowerCase() + "%");

            cq.where(cb.or(albumNamePredicate, artistNamePredicate));

            TypedQuery<Album> typedQuery = entityManager.createQuery(cq);
            return typedQuery.getResultList();
        } catch (Exception e) {
            LOGGER.error("Error searching albums with query: {}", query, e);
            return null;
        }
    }
    

    @Transactional
    public List<Album> getAllAlbums() {
        try {
            TypedQuery<Album> query = entityManager.createQuery(
                "SELECT DISTINCT a FROM Album a LEFT JOIN FETCH a.tracks", Album.class);
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.error("Error retrieving all albums", e);
            return null;
        }
    }
    
    @Transactional
    public List<Album> findAlbumsByGenre(Integer genreId) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Album> cq = cb.createQuery(Album.class);
            Root<Album> album = cq.from(Album.class);
            cq.where(cb.equal(album.get("genre").get("genreId"), genreId));
            TypedQuery<Album> query = entityManager.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.error("Error finding albums by genre ID: {}", genreId, e);
            return null;
        }
    }

    @Transactional
    public List<Album> findAlbumsByArtist(Integer artistId) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Album> cq = cb.createQuery(Album.class);
            Root<Album> album = cq.from(Album.class);
            cq.where(cb.equal(album.get("artist").get("artistId"), artistId));
            TypedQuery<Album> query = entityManager.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.error("Error finding albums by artist ID: {}", artistId, e);
            return null;
        }
    }
    @Transactional
    public void updateAlbumQuantity(Integer albumId, int quantity) {
        try {
            Album album = findAlbumById(albumId);
            if (album != null) {
                album.setAlbumQty(album.getAlbumQty() - quantity);
                entityManager.merge(album);
                LOGGER.info("Album quantity updated successfully for album ID: {}", albumId);
            } else {
                LOGGER.warn("Album with ID: {} not found", albumId);
            }
        } catch (Exception e) {
            LOGGER.error("Error updating album quantity for album ID: {}", albumId, e);
        }
    }
    
    
}