package org.music.app.codes.product.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.product.model.data.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class GenreRepository {
    public static final Logger LOGGER = LogManager.getLogger(GenreRepository.class);

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public boolean addGenre(Genre genre) {
        try {
            entityManager.persist(genre);
            LOGGER.info("Genre added successfully: {}", genre.getGenreName());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error adding Genre", e);
            return false;
        }
    }

    @Transactional
    public Genre findGenreById(Integer genreId) {
        try {
            return entityManager.find(Genre.class, genreId);
        } catch (Exception e) {
            LOGGER.error("Error finding Genre with ID: {}", genreId, e);
            return null;
        }
    }
    

    @Transactional
    public boolean updateGenre(Genre updatedGenre) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Genre> criteriaUpdate = cb.createCriteriaUpdate(Genre.class);
            Root<Genre> root = criteriaUpdate.from(Genre.class);

            criteriaUpdate.set("genreName", updatedGenre.getGenreName());
            criteriaUpdate.set("genreDesc", updatedGenre.getGenreDesc());
            criteriaUpdate.where(cb.equal(root.get("genreId"), updatedGenre.getGenreId()));

            entityManager.createQuery(criteriaUpdate).executeUpdate();
            LOGGER.info("Genre updated successfully: {}", updatedGenre.getGenreName());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error updating Genre", e);
            return false;
        }
    }

    @Transactional
    public boolean deleteGenre(Integer genreId) {
        try {
            Genre genre = entityManager.find(Genre.class, genreId);
            if (genre != null) {
                entityManager.remove(genre);
                LOGGER.info("Genre deleted successfully with ID: {}", genreId);
                return true;
            }
            LOGGER.warn("No Genre found with ID: {}", genreId);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error deleting Genre with ID: {}", genreId, e);
            return false;
        }
    }

    @Transactional
    public List<Genre> findAllGenres() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genre> cq = cb.createQuery(Genre.class);
            Root<Genre> root = cq.from(Genre.class);
            cq.select(root).distinct(true); 
            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            LOGGER.error("Error finding all Genres", e);
            return null;
        }
    }
}