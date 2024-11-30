package org.music.app.codes.product.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "track_id", nullable = false, unique = true)
    private Integer trackId;
    
    @Column(name = "track_name", nullable = false, length = 150)
    private String trackName;
    
    @Column(name = "track_music", nullable = false, length = 255)
    private String trackMusic;

    @ManyToOne
    @JoinColumn(name = "track_alb_id", referencedColumnName = "alb_id")
    @JsonIgnore
    private Album album;

    @ManyToOne
    @JoinColumn(name = "track_artist_id", referencedColumnName = "artist_id", nullable = false)
    private Artist artist;

	public Integer getTrackId() {
		return trackId;
	}

	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getTrackMusic() {
		return trackMusic;
	}

	public void setTrackMusic(String trackMusic) {
		this.trackMusic = trackMusic;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
    
    

}