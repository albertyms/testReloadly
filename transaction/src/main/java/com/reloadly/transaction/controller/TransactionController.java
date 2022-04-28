package com.reloadly.transaction.controller;

import com.reloadly.transaction.entity.AccountEntity;
import com.reloadly.transaction.entity.TransactionEntity;
import com.reloadly.transaction.request.TransactionRequest;
import com.reloadly.transaction.service.AccountService;
import com.reloadly.transaction.service.TransactionService;
import com.reloadly.transaction.util.TypeTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/transaction")
public class TransactionController {
    private final Logger logger = LogManager.getLogger(TransactionController.class);
    static final String ERROR_PROCESS = "Error: ";
    static final String ERROR_REQUEST = "Error trying to process the request: ";

    @Autowired
    TransactionService service;

    @Autowired
    AccountService accountService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody TransactionRequest request) {
        try {
            Optional<AccountEntity> account = accountService.findById(request.getAccountId());

            if (account.isPresent()) {
                AccountEntity accountEntity = account.get();
                TransactionEntity transaction = new TransactionEntity();
                if(request.getTypeTransaction().equalsIgnoreCase(TypeTransaction.DEPOSIT.name())) {
                    accountEntity.setAmount(accountEntity.getAmount() + request.getAmount());
                    transaction.setTypeTransaction(request.getTypeTransaction().toUpperCase());
                    transaction.setTransactionDate(new Date());
                    transaction.setAmount(request.getAmount());
                } else {
                    if(account.get().getAmount() >= request.getAmount()) {
                        accountEntity.setAmount(accountEntity.getAmount() - request.getAmount());
                        transaction.setTypeTransaction(request.getTypeTransaction().toUpperCase());
                        transaction.setTransactionDate(new Date());
                        transaction.setAmount(request.getAmount());
                        transaction.setAccount(accountEntity);
                    } else {
                        return new ResponseEntity<>("This account does not have a sufficient balance", HttpStatus.OK);
                    }
                }
                accountService.update(accountEntity);
                transaction = service.create(transaction);
                if (transaction.getId() != null) {
                    kafkaTemplate.send("notificationTopic", transaction);
                }
                return ResponseEntity.ok(transaction);
            } else {
                return new ResponseEntity<>("This account not exists", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error(ERROR_PROCESS, e);
            return new ResponseEntity<>(ERROR_REQUEST, HttpStatus.BAD_REQUEST);
        }
    }


}
