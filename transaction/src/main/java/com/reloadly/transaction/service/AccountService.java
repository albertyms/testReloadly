package com.reloadly.transaction.service;

import com.reloadly.transaction.entity.AccountEntity;

import java.util.Optional;

public interface AccountService {

    AccountEntity update(AccountEntity account);

    Optional<AccountEntity> findById(Long id);

}
