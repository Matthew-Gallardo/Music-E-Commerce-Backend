package org.music.app.codes.product.model.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.music.app.codes.transaction.model.data.CartItems;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alb_id", nullable = false, unique = true)
    private Integer albumId;

    @Column(name = "alb_name", nullable = false, length = 150)
    private String albumName;

    @Column(name = "alb_image", columnDefinition = "TEXT")
    private String albumImage;

    @Column(name = "alb_desc")
    private String albumDesc;

    @Column(name = "alb_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal albumPrice;

    @Column(name = "alb_qty")
    private Integer albumQty;

    @ManyToOne
    @JoinColumn(name = "alb_artist_id", referencedColumnName = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "alb_genre_id", referencedColumnName = "genre_id", nullable = false)
    private Genre genre;

    @OneToMany(mappedBy = "album", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Track> tracks = new ArrayList<>();
    
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<CartItems> cartItems = new ArrayList<>();

    // Getters and setters
    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }

    public BigDecimal getAlbumPrice() {
        return albumPrice;
    }

    public void setAlbumPrice(BigDecimal albumPrice) {
        this.albumPrice = albumPrice;
    }

    public Integer getAlbumQty() {
        return albumQty;
    }

    public void setAlbumQty(Integer albumQty) {
        this.albumQty = albumQty;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}