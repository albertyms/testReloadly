package com.reloadly.transaction.service;

import com.reloadly.transaction.entity.AccountEntity;

import java.util.Optional;

public interface AccountService {

    AccountEntity create(AccountEntity account);

    AccountEntity update(AccountEntity account);

    Optional<AccountEntity> findById(Long id);

}
