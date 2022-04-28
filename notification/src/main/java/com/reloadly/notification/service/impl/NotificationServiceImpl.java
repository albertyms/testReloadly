package com.reloadly.notification.service.impl;

import com.reloadly.notification.entity.NotificationEntity;
import com.reloadly.notification.entity.TransactionEntity;
import com.reloadly.notification.repository.AccountRepository;
import com.reloadly.notification.repository.NotificationRepository;
import com.reloadly.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import java.util.Date;

public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository repository;

    @Autowired
    AccountRepository accountRepository;

        @KafkaListener(topics = "notificationTopic", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(TransactionEntity transaction, Acknowledgment ack) {

        NotificationEntity notification = new NotificationEntity();

        notification.setAccount(accountRepository.findById(transaction.getAccount().getId()).get());
        notification.setCreationDate(new Date());
        notification.setTransaction(transaction);
        notification.setMessage("Test");
        repository.save(notification);
        ack.acknowledge();

    }
}
