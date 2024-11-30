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
import org.music.app.codes.product.repository.AlbumRepository;
import org.music.app.codes.product.repository.ArtistRepository;
import org.music.app.codes.product.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;

@SpringBootTest
public class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @Transactional
    @Rollback(true)
    public void testAddAlbum() {
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
        album.setAlbumDesc("Revolutionize the Hip-Hop Scene");
        album.setAlbumImage("https://res.cloudinary.com/do3op0083/image/upload/f_auto,q_auto/v1/SBC%20Capstone/Album%20Covers/kanye1");
        album.setAlbumQty(20);

        // Add the album to the repository
        boolean res = albumRepository.addAlbum(album);
        assertTrue(res, "Album should be added successfully");

        // Verify the album was added
        Album addedAlbum = albumRepository.findAlbumById(album.getAlbumId());
        assertNotNull(addedAlbum, "Added album should not be null");
        assertEquals(artist.getArtistId(), addedAlbum.getArtist().getArtistId(), "Artist ID should match");
        assertEquals(genre.getGenreId(), addedAlbum.getGenre().getGenreId(), "Genre ID should match");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindAlbumById() {
        // Create a new Album instance
        Artist artist = new Artist();
        artist.setArtistName("Test Artist");
        artistRepository.addArtist(artist);

        Genre genre = new Genre();
        genre.setGenreName("Test Genre");
        genreRepository.addGenre(genre);

        Album album = new Album();
        album.setAlbumName("Test Album");
        album.setAlbumPrice(BigDecimal.valueOf(10.00));
        album.setArtist(artist);
        album.setGenre(genre);
        albumRepository.addAlbum(album);

        // Find the album by ID
        Album foundAlbum = albumRepository.findAlbumById(album.getAlbumId());
        assertNotNull(foundAlbum);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateAlbum() {
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

        // Update the album
        album.setAlbumName("Updated Album");
        boolean result = albumRepository.updateAlbum(album);
        assertTrue(result);

        // Verify the album was updated
        Album updatedAlbum = albumRepository.findAlbumById(album.getAlbumId());
        assertEquals("Updated Album", updatedAlbum.getAlbumName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteAlbum() {
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

        // Delete the album
        boolean result = albumRepository.deleteAlbum(album.getAlbumId());
        assertTrue(result);

        // Verify the album was deleted
        Album deletedAlbum = albumRepository.findAlbumById(album.getAlbumId());
        assertNull(deletedAlbum);
    }
}