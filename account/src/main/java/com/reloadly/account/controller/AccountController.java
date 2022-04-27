package com.reloadly.account.controller;

import com.reloadly.account.entity.AccountEntity;
import com.reloadly.account.entity.UserEntity;
import com.reloadly.account.request.AccountRequest;
import com.reloadly.account.service.AccountService;
import com.reloadly.account.service.impl.UserDetailsServiceExtImpl;
import com.reloadly.account.util.TypeAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {
    private final Logger logger = LogManager.getLogger(AccountController.class);
    static final String ERROR_PROCESS = "Error: ";
    static final String ERROR_REQUEST = "Error trying to process the request: ";

    @Autowired
    AccountService service;

    @Autowired
    UserDetailsServiceExtImpl userDetailsService;

    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody AccountRequest request) {
        try {
            long numberAccount = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
            AccountEntity account = new AccountEntity();
            account.setAmount(request.getAmount());
            account.setTypeAccount(TypeAccount.valueOf(request.getTypeAccount()).toString());
            account.setUser(userDetailsService.findUserByUsername(request.getUserName().toLowerCase()));
            account.setNumberAccount(numberAccount);
            account.setCreationDate(new Date());
            account = service.create(account);
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            logger.error(ERROR_PROCESS, e);
            return new ResponseEntity<>(ERROR_REQUEST, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            Optional<AccountEntity> account = service.findById(id);
            return account.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>("This account not exists", HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            logger.error(ERROR_PROCESS, e);
            return new ResponseEntity<>(ERROR_REQUEST, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam String userName) {
        UserEntity user = userDetailsService.findUserByUsername(userName.toLowerCase());
        if(user != null) {
            return ResponseEntity.ok(service.findAllByUser(user));
        } else {
            return new ResponseEntity<>("This user not exists", HttpStatus.NOT_FOUND);
        }
    }

}
