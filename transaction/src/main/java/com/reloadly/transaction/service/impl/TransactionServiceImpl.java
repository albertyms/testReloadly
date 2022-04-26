package com.reloadly.transaction.service.impl;

import com.reloadly.transaction.entity.TransactionEntity;
import com.reloadly.transaction.repository.TransactionRepository;
import com.reloadly.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository repository;

    @Override
    public TransactionEntity create(TransactionEntity transaction) {

        return repository.save(transaction);

    }
}
