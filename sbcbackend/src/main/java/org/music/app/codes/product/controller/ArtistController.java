package org.music.app.codes.product.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.product.model.data.Artist;
import org.music.app.codes.product.model.forms.ArtistForm;
import org.music.app.codes.product.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    private static final Logger LOGGER = LogManager.getLogger(ArtistController.class);

    @Autowired
    private ArtistRepository repository;

    @PostMapping("/create")
    public boolean createArtist(@RequestBody ArtistForm artistForm) {
        try {
            Artist artist = new Artist();
            artist.setArtistName(artistForm.getArtistName());
            artist.setArtistNumber(artistForm.getArtistNumber());
            artist.setArtistEmail(artistForm.getArtistEmail());
            artist.setArtistLocation(artistForm.getArtistLocation());
            return repository.addArtist(artist);
        } catch (Exception e) {
            LOGGER.error("Error creating artist", e);
            return false;
        }
    }

    @PutMapping("/update/{id}")
    public boolean updateArtist(@PathVariable Integer id, @RequestBody ArtistForm artistForm) {
        try {
            Artist artist = repository.findArtistById(id);
            if (artist != null) {
                artist.setArtistName(artistForm.getArtistName());
                artist.setArtistNumber(artistForm.getArtistNumber());
                artist.setArtistEmail(artistForm.getArtistEmail());
                artist.setArtistLocation(artistForm.getArtistLocation());
                return repository.updateArtist(artist);
            } else {
                LOGGER.warn("Artist not found with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("Error updating artist", e);
            return false;
        }
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteArtist(@PathVariable Integer id) {
        try {
            return repository.deleteArtist(id);
        } catch (Exception e) {
            LOGGER.error("Error deleting artist", e);
            return false;
        }
    }

    @GetMapping("/all")
    public List<Artist> getAllArtists() {
        try {
            return repository.findAllArtists();
        } catch (Exception e) {
            LOGGER.error("Error getting all artists", e);
            return null;
        }
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable Integer id) {
        try {
            return repository.findArtistById(id);
        } catch (Exception e) {
            LOGGER.error("Error getting artist by ID", e);
            return null;
        }
    }
}