package org.music.app.codes.account.model.data;

import java.util.ArrayList;
import java.util.List;

import org.music.app.codes.transaction.model.data.Cart;
import org.music.app.codes.transaction.model.data.Transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") 
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "user_id", nullable = false, unique = true)
    private Integer userId;

    @Column(name = "user_firstname", nullable = false, length = 100)
    private String userFirstname = "Anonymous";

    @Column(name = "user_lastname", nullable = false, length = 100) 
    private String userLastname = "User";
    
    @Column(name = "user_email", nullable = false, unique = true, length = 100)
    private String userEmail = "noemail@domain.com";

    @Column(name = "user_mobile", nullable = false, length = 15) 
    private String userMobile = "Not Provided";

    @Column(name = "user_street") 
    private String userStreet = "N/A";

    @Column(name = "user_city", length = 150) 
    private String userCity = "Unknown City";

    @Column(name = "user_state", length = 150) 
    private String userState = "Unknown State";

    @Column(name = "user_zipcode", length = 20)
    private String userZipcode = "00000";

    @Column(name = "user_country", length = 150) 
    private String userCountry = "Unknown";

    @Column(name = "user_billing_address") 
    private String userBillingAddress = "Same as Shipping";

    @Column(name = "user_shipping_address") 
    private String userShippingAddress = "N/A";
    
    
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Login login;
    
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList<>();
    
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Cart> carts = new ArrayList<>();



	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUserFirstname() {
		return userFirstname;
	}


	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}


	public String getUserLastname() {
		return userLastname;
	}


	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserMobile() {
		return userMobile;
	}


	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}


	public String getUserStreet() {
		return userStreet;
	}


	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}


	public String getUserCity() {
		return userCity;
	}


	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}


	public String getUserState() {
		return userState;
	}


	public void setUserState(String userState) {
		this.userState = userState;
	}


	public String getUserZipcode() {
		return userZipcode;
	}


	public void setUserZipcode(String userZipcode) {
		this.userZipcode = userZipcode;
	}


	public String getUserCountry() {
		return userCountry;
	}


	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}


	public String getUserBillingAddress() {
		return userBillingAddress;
	}


	public void setUserBillingAddress(String userBillingAddress) {
		this.userBillingAddress = userBillingAddress;
	}


	public String getUserShippingAddress() {
		return userShippingAddress;
	}


	public void setUserShippingAddress(String userShippingAddress) {
		this.userShippingAddress = userShippingAddress;
	}


	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}


	public List<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}


	public List<Cart> getCarts() {
		return carts;
	}


	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}


    
    

    
    
	
    
    
}
