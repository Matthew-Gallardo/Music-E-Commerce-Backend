package org.music.app.codes.product.model.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "artist_id", nullable = false, unique = true)
    private Integer artistId;
    
    @Column(name = "artist_name", nullable = false, length = 100)
    private String artistName;
    
    @Column(name = "artist_number", length = 100)
    private String artistNumber;
    
    @Column(name = "artist_email", length = 100)
    private String artistEmail;
    
    @Column(name = "artist_location", length = 150)
    private String artistLocation;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Album> albums;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Track> tracks;

	public Integer getArtistId() {
		return artistId;
	}

	public void setArtistId(Integer artistId) {
		this.artistId = artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
	

	public String getArtistNumber() {
		return artistNumber;
	}

	public void setArtistNumber(String artistNumber) {
		this.artistNumber = artistNumber;
	}

	public String getArtistEmail() {
		return artistEmail;
	}

	public void setArtistEmail(String artistEmail) {
		this.artistEmail = artistEmail;
	}

	public String getArtistLocation() {
		return artistLocation;
	}

	public void setArtistLocation(String artistLocation) {
		this.artistLocation = artistLocation;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

    
}