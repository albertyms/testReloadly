package com.reloadly.transaction.service;

import com.reloadly.transaction.entity.TransactionEntity;

public interface TransactionService {

    TransactionEntity create(TransactionEntity transaction);

}
