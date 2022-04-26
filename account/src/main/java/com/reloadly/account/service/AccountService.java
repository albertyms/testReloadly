package com.reloadly.account.service;

import com.reloadly.account.entity.AccountEntity;
import com.reloadly.account.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountEntity create(AccountEntity account);

    AccountEntity update(AccountEntity account);

    Optional<AccountEntity> findById(Long id);

    List<AccountEntity> findAllByUser(UserEntity user);

}
