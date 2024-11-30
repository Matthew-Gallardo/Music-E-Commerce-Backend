package org.music.app.codes.transaction.service;

import org.music.app.codes.transaction.model.data.Transaction;
import org.music.app.codes.transaction.repository.TransactionRepository;
import org.music.app.codes.account.model.data.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction, Integer userId) {
        Users user = transactionRepository.findUserById(userId);
        if (user != null) {
            transaction.setUsers(user);
            return transactionRepository.save(transaction);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction updateTransaction(Integer id, Transaction transaction) {
        return transactionRepository.update(id, transaction);
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.delete(id);
    }
}