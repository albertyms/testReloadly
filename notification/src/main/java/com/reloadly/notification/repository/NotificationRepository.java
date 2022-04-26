package com.reloadly.notification.repository;

import com.reloadly.notification.entity.NotificationEntity;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<NotificationEntity, Long> {



}
