package org.music.app.codes.product.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.product.model.data.Genre;
import org.music.app.codes.product.model.forms.GenreForm;
import org.music.app.codes.product.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private static final Logger LOGGER = LogManager.getLogger(GenreController.class);

    @Autowired
    private GenreRepository repository;

    @PostMapping("/create")
    public boolean createGenre(@RequestBody GenreForm genreForm) {
        LOGGER.info("Creating genre with name: {}", genreForm.getGenreName());
        Genre genre = new Genre();
        genre.setGenreName(genreForm.getGenreName());
        genre.setGenreDesc(genreForm.getGenreDesc());
        boolean result = repository.addGenre(genre);
        LOGGER.info("Genre creation status: {}", result);
        return result;
    }

    @PutMapping("/update/{id}")
    public boolean updateGenre(@PathVariable Integer id, @RequestBody GenreForm genreForm) {
        LOGGER.info("Updating genre with ID: {}", id);
        Genre genre = repository.findGenreById(id);
        if (genre != null) {
            genre.setGenreName(genreForm.getGenreName());
            genre.setGenreDesc(genreForm.getGenreDesc());
            boolean result = repository.updateGenre(genre);
            LOGGER.info("Genre update status: {}", result);
            return result;
        }
        LOGGER.warn("Genre with ID: {} not found", id);
        return false;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteGenre(@PathVariable Integer id) {
        LOGGER.info("Deleting genre with ID: {}", id);
        boolean result = repository.deleteGenre(id);
        LOGGER.info("Genre deletion status: {}", result);
        return result;
    }

    @GetMapping("/all")
    public List<Genre> getAllGenres() {
        LOGGER.info("Fetching all genres");
        List<Genre> genres = repository.findAllGenres();
        LOGGER.info("Number of genres fetched: {}", genres.size());
        return genres;
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Integer id) {
        LOGGER.info("Fetching genre with ID: {}", id);
        Genre genre = repository.findGenreById(id);
        if (genre != null) {
            LOGGER.info("Genre found: {}", genre.getGenreName());
        } else {
            LOGGER.warn("Genre with ID: {} not found", id);
        }
        return genre;
    }
}