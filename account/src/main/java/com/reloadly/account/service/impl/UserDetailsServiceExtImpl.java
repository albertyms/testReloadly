package com.reloadly.account.service.impl;

import com.reloadly.account.entity.UserEntity;
import com.reloadly.account.repository.UserRepository;

import com.reloadly.account.service.UserDetailsServiceExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceExtImpl implements UserDetailsServiceExt {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepository userPRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(userEntity);
    }

    @Override
    public UserEntity findUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }


}
