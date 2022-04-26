package com.reloadly.account.repository;

import com.reloadly.account.entity.RoleEntity;
import com.reloadly.account.util.RoleEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleEnum name);

}
