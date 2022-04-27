package com.reloadly.account.repository;

import com.reloadly.account.entity.AccountEntity;
import com.reloadly.account.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

    List<AccountEntity> findByUser(UserEntity user);

}
