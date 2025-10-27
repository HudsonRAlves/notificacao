package com.estudos.notificacao.repositorys;

import com.estudos.notificacao.domain.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
