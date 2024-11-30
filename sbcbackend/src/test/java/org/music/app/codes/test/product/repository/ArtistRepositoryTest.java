package org.music.app.codes.test.product.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.music.app.codes.product.model.data.Artist;
import org.music.app.codes.product.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;


@SpringBootTest
public class ArtistRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    @Transactional
    @Rollback(true) 
    public void testAddArtist() {
        Artist artist = new Artist();
        artist.setArtistName("Kanye West");

        boolean result = artistRepository.addArtist(artist);
        assertTrue(result);
    }

    @Test
    @Transactional
    @Rollback(true) 
    public void testFindArtistById() {
        Artist artist = new Artist();
        artist.setArtistName("Test Artist");
        artistRepository.addArtist(artist);

        Artist foundArtist = artistRepository.findArtistById(artist.getArtistId());
        assertNotNull(foundArtist);
        assertEquals("Test Artist", foundArtist.getArtistName());
    }

    @Test
    @Transactional
    @Rollback(true) 
    public void testUpdateArtist() {
        Artist artist = new Artist();
        artist.setArtistName("Test Artist");
        artistRepository.addArtist(artist);

        artist.setArtistName("Updated Artist");
        boolean result = artistRepository.updateArtist(artist);
        assertTrue(result);

        Artist updatedArtist = artistRepository.findArtistById(artist.getArtistId());
        assertEquals("Updated Artist", updatedArtist.getArtistName());
    }

    @Test
    @Transactional
    @Rollback(true) 
    public void testDeleteArtist() {
        Artist artist = new Artist();
        artist.setArtistName("Test Artist");
        artistRepository.addArtist(artist);

        boolean result = artistRepository.deleteArtist(artist.getArtistId());
        assertTrue(result);

        Artist deletedArtist = artistRepository.findArtistById(artist.getArtistId());
        assertNull(deletedArtist);
    }
}