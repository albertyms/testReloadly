package com.reloadly.account.service;

import com.reloadly.account.entity.UserEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsServiceExt extends org.springframework.security.core.userdetails.UserDetailsService {

    UserEntity findUserByUsername(String username) throws UsernameNotFoundException;

}
