package org.music.app.codes.transaction.model.data;

import org.music.app.codes.product.model.data.Album;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id", nullable = false)
    private Integer cartItemId;
    
    @Column(name = "cart_quantity")
    private Integer cartQuantity;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    @JsonIgnoreProperties("cartItems")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "cart_alb_id", referencedColumnName = "alb_id")
    private Album album;

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(Integer cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
    
    



}
