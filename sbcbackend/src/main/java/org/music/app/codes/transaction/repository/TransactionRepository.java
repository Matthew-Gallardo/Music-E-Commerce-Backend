package org.music.app.codes.transaction.repository;

import org.music.app.codes.transaction.model.data.Transaction;
import org.music.app.codes.account.model.data.Users;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public class TransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Transaction save(Transaction transaction) {
        if (transaction.getTransactionId() == null) {
            entityManager.persist(transaction);
            return transaction;
        } else {
            return entityManager.merge(transaction);
        }
    }

    public Transaction findById(Integer id) {
        return entityManager.find(Transaction.class, id);
    }

    public List<Transaction> findAll() {
        return entityManager.createQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();
    }

    @Transactional
    public Transaction update(Integer id, Transaction transaction) {
        Transaction existingTransaction = findById(id);
        if (existingTransaction != null) {
            existingTransaction.setTransactionTotalAmount(transaction.getTransactionTotalAmount());
            existingTransaction.setTransactionStatus(transaction.getTransactionStatus());
            existingTransaction.setTransactionDate(transaction.getTransactionDate());
            return entityManager.merge(existingTransaction);
        }
        return null;
    }

    @Transactional
    public void delete(Integer id) {
        Transaction transaction = findById(id);
        if (transaction != null) {
            entityManager.remove(transaction);
        }
    }

    public Users findUserById(Integer userId) {
        return entityManager.find(Users.class, userId);
    }
}