package org.music.app.codes.product.model.forms;


import java.math.BigDecimal;

public class AlbumForm {

    private String albumName;
    private String albumImage;
    private String albumDesc;
    private BigDecimal albumPrice;
    private Integer albumQty;
    private Integer artistId;
    private Integer genreId;


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

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }
}