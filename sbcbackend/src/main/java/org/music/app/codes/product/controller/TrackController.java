package org.music.app.codes.product.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.product.model.data.Track;
import org.music.app.codes.product.model.data.Artist;
import org.music.app.codes.product.model.data.Album;
import org.music.app.codes.product.model.forms.TrackForm;
import org.music.app.codes.product.repository.TrackRepository;
import org.music.app.codes.product.repository.ArtistRepository;
import org.music.app.codes.product.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
public class TrackController {
    private static final Logger LOGGER = LogManager.getLogger(TrackController.class);

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addTrack(@RequestBody TrackForm trackForm) {
        Track track = convertToTrack(trackForm);
        if (trackRepository.addTrack(track)) {
            return ResponseEntity.ok("Track added successfully");
        } else {
            return ResponseEntity.status(500).body("Error adding track");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> findTrackById(@PathVariable Integer id) {
        Track track = trackRepository.findTrackById(id);
        if (track != null) {
            return ResponseEntity.ok(track);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTrack(@PathVariable Integer id, @RequestBody TrackForm trackForm) {
        Track track = convertToTrack(trackForm);
        track.setTrackId(id); // Set the trackId for the update operation
        if (trackRepository.updateTrack(track)) {
            return ResponseEntity.ok("Track updated successfully");
        } else {
            return ResponseEntity.status(500).body("Error updating track");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTrack(@PathVariable Integer id) {
        if (trackRepository.deleteTrack(id)) {
            return ResponseEntity.ok("Track deleted successfully");
        } else {
            return ResponseEntity.status(500).body("Error deleting track");
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Track>> getAllTracks() {
        List<Track> tracks = trackRepository.getAllTracks();
        if (tracks != null) {
            return ResponseEntity.ok(tracks);
        } else {
            return ResponseEntity.status(500).body(null);
        }
    }

    private Track convertToTrack(TrackForm trackForm) {
        Track track = new Track();
        track.setTrackName(trackForm.getTrackName());
        track.setTrackMusic(trackForm.getTrackMusic());

        Artist artist = artistRepository.findArtistById(trackForm.getArtistId());
        Album album = albumRepository.findAlbumById(trackForm.getAlbumId());

        if (artist != null) {
            track.setArtist(artist);
        } else {
            LOGGER.warn("Artist with ID: {} not found", trackForm.getArtistId());
        }

        if (album != null) {
            track.setAlbum(album);
        } else {
            LOGGER.warn("Album with ID: {} not found", trackForm.getAlbumId());
        }

        return track;
    }
}