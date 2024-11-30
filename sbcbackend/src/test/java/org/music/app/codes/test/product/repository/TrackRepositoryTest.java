package org.music.app.codes.test.product.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.music.app.codes.product.model.data.Album;
import org.music.app.codes.product.model.data.Artist;
import org.music.app.codes.product.model.data.Genre;
import org.music.app.codes.product.model.data.Track;
import org.music.app.codes.product.repository.AlbumRepository;
import org.music.app.codes.product.repository.ArtistRepository;
import org.music.app.codes.product.repository.GenreRepository;
import org.music.app.codes.product.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;

@SpringBootTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @Transactional
    @Rollback(true)
    public void testAddTrack() {
        // Create a new Artist instance
        Artist artist = new Artist();
        artist.setArtistName("Kanye West");
        artistRepository.addArtist(artist);

        // Create a new Genre instance
        Genre genre = new Genre();
        genre.setGenreName("Hip-Hop");
        genreRepository.addGenre(genre);

        // Create a new Album instance
        Album album = new Album();
        album.setAlbumName("The College Drop-out");
        album.setAlbumPrice(BigDecimal.valueOf(2000.99));
        album.setArtist(artist);
        album.setGenre(genre);
        albumRepository.addAlbum(album);

        // Create a new Track instance
        Track track = new Track();
        track.setTrackName("All Falls Down");
        track.setTrackMusic("https://res.cloudinary.com/do3op0083/video/upload/f_auto:video,q_auto/v1/SBC%20Capstone/songs/collegedropout/yzfw0lty3j073djdbhwk");
        track.setArtist(artist);
        track.setAlbum(album);

        // Add the track to the repository
        boolean result = trackRepository.addTrack(track);
        assertTrue(result, "Track should be added successfully");

        // Verify the track was added
        Track addedTrack = trackRepository.findTrackById(track.getTrackId());
        assertNotNull(addedTrack, "Added track should not be null");
        assertEquals(album.getAlbumId(), addedTrack.getAlbum().getAlbumId(), "Album ID should match");
        assertEquals(artist.getArtistId(), addedTrack.getArtist().getArtistId(), "Artist ID should match");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindTrackById() {
        // Create a new Artist instance
        Artist artist = new Artist();
        artist.setArtistName("Test Artist");
        artistRepository.addArtist(artist);

        // Create a new Genre instance
        Genre genre = new Genre();
        genre.setGenreName("Test Genre");
        genreRepository.addGenre(genre);

        // Create a new Album instance
        Album album = new Album();
        album.setAlbumName("Test Album");
        album.setAlbumPrice(BigDecimal.valueOf(10.00));
        album.setArtist(artist);
        album.setGenre(genre);
        albumRepository.addAlbum(album);

        // Create a new Track instance
        Track track = new Track();
        track.setTrackName("Test Track");
        track.setTrackMusic("Test Music");
        track.setAlbum(album);
        track.setArtist(artist);
        trackRepository.addTrack(track);

        // Find the track by ID
        Track foundTrack = trackRepository.findTrackById(track.getTrackId());
        assertNotNull(foundTrack);
        assertEquals("Test Track", foundTrack.getTrackName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateTrack() {
        // Create a new Artist instance
        Artist artist = new Artist();
        artist.setArtistName("Test Artist");
        artistRepository.addArtist(artist);

        // Create a new Genre instance
        Genre genre = new Genre();
        genre.setGenreName("Test Genre");
        genreRepository.addGenre(genre);

        // Create a new Album instance
        Album album = new Album();
        album.setAlbumName("Test Album");
        album.setAlbumPrice(BigDecimal.valueOf(10.00));
        album.setArtist(artist);
        album.setGenre(genre);
        albumRepository.addAlbum(album);

        // Create a new Track instance
        Track track = new Track();
        track.setTrackName("Test Track");
        track.setTrackMusic("Test Music");
        track.setAlbum(album);
        track.setArtist(artist);
        trackRepository.addTrack(track);

        // Update the track
        track.setTrackName("Updated Track");
        boolean result = trackRepository.updateTrack(track);
        assertTrue(result);

        // Verify the track was updated
        Track updatedTrack = trackRepository.findTrackById(track.getTrackId());
        assertEquals("Updated Track", updatedTrack.getTrackName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteTrack() {
        // Create a new Artist instance
        Artist artist = new Artist();
        artist.setArtistName("Test Artist");
        artistRepository.addArtist(artist);

        // Create a new Genre instance
        Genre genre = new Genre();
        genre.setGenreName("Test Genre");
        genreRepository.addGenre(genre);

        // Create a new Album instance
        Album album = new Album();
        album.setAlbumName("Test Album");
        album.setAlbumPrice(BigDecimal.valueOf(10.00));
        album.setArtist(artist);
        album.setGenre(genre);
        albumRepository.addAlbum(album);

        // Create a new Track instance
        Track track = new Track();
        track.setTrackName("Test Track");
        track.setTrackMusic("Test Music");
        track.setAlbum(album);
        track.setArtist(artist);
        trackRepository.addTrack(track);

        // Delete the track
        boolean result = trackRepository.deleteTrack(track.getTrackId());
        assertTrue(result);

        // Verify the track was deleted
        Track deletedTrack = trackRepository.findTrackById(track.getTrackId());
        assertNull(deletedTrack);
    }
}