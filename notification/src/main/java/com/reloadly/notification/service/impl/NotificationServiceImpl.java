package com.reloadly.notification.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.reloadly.notification.entity.AccountEntity;
import com.reloadly.notification.entity.NotificationEntity;
import com.reloadly.notification.entity.TransactionEntity;
import com.reloadly.notification.repository.AccountRepository;
import com.reloadly.notification.repository.NotificationRepository;
import com.reloadly.notification.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class NotificationServiceImpl {

    @Autowired
    NotificationRepository repository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @KafkaListener(topics = "notificationTopic", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String transactionJson) {

        NotificationEntity notification = new NotificationEntity();

        JsonObject jsonObject = new Gson().fromJson(transactionJson, JsonObject.class);

        Optional<AccountEntity> account = accountRepository.findById(jsonObject.get("account").getAsJsonObject().get("id").getAsLong());

        if (account.isPresent()) {
            Optional<TransactionEntity> transaction = transactionRepository.findById(jsonObject.get("id").getAsLong());
            notification.setAccount(account.get());
            notification.setCreationDate(new Date());
            notification.setTransaction(transaction.orElse(null));
            notification.setMessage("Test");
            repository.save(notification);
        }

    }
}
