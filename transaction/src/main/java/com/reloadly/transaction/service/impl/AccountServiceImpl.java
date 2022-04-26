package com.reloadly.transaction.service.impl;

import com.reloadly.transaction.entity.AccountEntity;
import com.reloadly.transaction.repository.AccountRepository;
import com.reloadly.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repository;

    @Override
    public AccountEntity create(AccountEntity account) {
        return repository.save(account);
    }

    @Override
    public AccountEntity update(AccountEntity account) {
        return repository.save(account);
    }

    @Override
    public Optional<AccountEntity> findById(Long id) {
        return repository.findById(id);
    }

}
