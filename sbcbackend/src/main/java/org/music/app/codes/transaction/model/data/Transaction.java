package org.music.app.codes.transaction.model.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.music.app.codes.account.model.data.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", nullable = false)
    private Integer transactionId;

    @Column(name = "transaction_total_amount", precision = 10, scale = 2)
    private BigDecimal transactionTotalAmount;

    @Column(name = "transaction_status", length = 150)
    private String transactionStatus;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
    
    @ManyToOne
	@JoinColumn(name = "transaction_user_id", referencedColumnName = "user_id")
	private Users users;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getTransactionTotalAmount() {
		return transactionTotalAmount;
	}

	public void setTransactionTotalAmount(BigDecimal transactionTotalAmount) {
		this.transactionTotalAmount = transactionTotalAmount;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
    
	

}
