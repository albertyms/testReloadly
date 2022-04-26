package com.reloadly.transaction.repository;

import com.reloadly.transaction.entity.RoleEntity;
import com.reloadly.transaction.util.RoleEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleEnum name);

}
