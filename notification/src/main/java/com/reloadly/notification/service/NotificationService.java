package com.reloadly.notification.service;


import com.reloadly.notification.entity.TransactionEntity;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface NotificationService {

    void consume(@Payload TransactionEntity transaction, Acknowledgment ack);

}
