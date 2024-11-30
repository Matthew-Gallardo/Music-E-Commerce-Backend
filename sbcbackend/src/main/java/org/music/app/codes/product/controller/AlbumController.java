package org.music.app.codes.product.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.product.model.data.Album;
import org.music.app.codes.product.model.data.Artist;
import org.music.app.codes.product.model.data.Genre;
import org.music.app.codes.product.model.forms.AlbumForm;
import org.music.app.codes.product.repository.AlbumRepository;
import org.music.app.codes.product.repository.ArtistRepository;
import org.music.app.codes.product.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
public class AlbumController {
    private static final Logger LOGGER = LogManager.getLogger(AlbumController.class);

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @PostMapping("/add")
    public boolean addAlbum(@RequestBody AlbumForm albumForm) {
        LOGGER.info("Request to add album: {}", albumForm.getAlbumName());
        Album album = mapToAlbum(albumForm);
        return albumRepository.addAlbum(album);
    }

    @GetMapping("/{id}")
    public Album findAlbumById(@PathVariable Integer id) {
        LOGGER.info("Request to find album with ID: {}", id);
        return albumRepository.findAlbumById(id);
    }

    @PutMapping("/update/{id}")
    public boolean updateAlbum(@PathVariable Integer id, @RequestBody AlbumForm albumForm) {
        LOGGER.info("Request to update album with ID: {}", id);
        Album album = mapToAlbum(albumForm);
        album.setAlbumId(id); 
        return albumRepository.updateAlbum(album);
    }
    @GetMapping("/search")
    public List<Album> searchAlbums(@RequestParam String query) {
        LOGGER.info("Request to search albums with query: {}", query);
        return albumRepository.searchAlbums(query);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteAlbum(@PathVariable Integer id) {
        LOGGER.info("Request to delete album with ID: {}", id);
        return albumRepository.deleteAlbum(id);
    }

    @GetMapping("/all")
    public List<Album> getAllAlbums() {
        LOGGER.info("Request to get all albums");
        return albumRepository.getAllAlbums();
    }
    
    @GetMapping("/genre/{genreId}")
    public List<Album> getAlbumsByGenre(@PathVariable Integer genreId) {
        LOGGER.info("Request to get albums by genre ID: {}", genreId);
        return albumRepository.findAlbumsByGenre(genreId);
    }

    @GetMapping("/artist/{artistId}")
    public List<Album> getAlbumsByArtist(@PathVariable Integer artistId) {
        LOGGER.info("Request to get albums by artist ID: {}", artistId);
        return albumRepository.findAlbumsByArtist(artistId);
    }

    private Album mapToAlbum(AlbumForm albumForm) {
        Album album = new Album();
        album.setAlbumName(albumForm.getAlbumName());
        album.setAlbumImage(albumForm.getAlbumImage());
        album.setAlbumDesc(albumForm.getAlbumDesc());
        album.setAlbumPrice(albumForm.getAlbumPrice());
        album.setAlbumQty(albumForm.getAlbumQty());

        Artist artist = artistRepository.findArtistById(albumForm.getArtistId());
        album.setArtist(artist);

        Genre genre = genreRepository.findGenreById(albumForm.getGenreId());
        album.setGenre(genre);

        return album;
    }
    
    
}