package com.reloadly.account.service.impl;

import com.reloadly.account.entity.AccountEntity;
import com.reloadly.account.entity.UserEntity;
import com.reloadly.account.repository.AccountRepository;
import com.reloadly.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<AccountEntity> findAllByUser(UserEntity user) {
        return repository.findByUser(user);
    }
}
