package org.music.app.codes.test.product.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.music.app.codes.product.model.data.Genre;
import org.music.app.codes.product.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;


@SpringBootTest
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @Transactional
    @Rollback(true) 
    public void testAddGenre() {
        Genre genre = new Genre();
        genre.setGenreName("Hiphop");

        boolean result = genreRepository.addGenre(genre);
        assertTrue(result);
    }

    @Test
    @Transactional
    @Rollback(true) 
    public void testFindGenreById() {
        Genre genre = new Genre();
        genre.setGenreName("Test Genre");
        genreRepository.addGenre(genre);

        Genre foundGenre = genreRepository.findGenreById(genre.getGenreId());
        assertNotNull(foundGenre);
        assertEquals("Test Genre", foundGenre.getGenreName());
    }

    @Test
    @Transactional
    @Rollback(true) 
    public void testUpdateGenre() {
        Genre genre = new Genre();
        genre.setGenreName("Test Genre");
        genreRepository.addGenre(genre);

        genre.setGenreName("Updated Genre");
        boolean result = genreRepository.updateGenre(genre);
        assertTrue(result);

        Genre updatedGenre = genreRepository.findGenreById(genre.getGenreId());
        assertEquals("Updated Genre", updatedGenre.getGenreName());
    }

    @Test
    @Transactional
    @Rollback(true) 
    public void testDeleteGenre() {
        Genre genre = new Genre();
        genre.setGenreName("Test Genre");
        genreRepository.addGenre(genre);

        boolean result = genreRepository.deleteGenre(genre.getGenreId());
        assertTrue(result);

        Genre deletedGenre = genreRepository.findGenreById(genre.getGenreId());
        assertNull(deletedGenre);
    }
}