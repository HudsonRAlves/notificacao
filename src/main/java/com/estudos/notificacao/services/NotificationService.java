package com.estudos.notificacao.services;

import com.estudos.notificacao.domain.dtos.NotificationInDTO;
import com.estudos.notificacao.domain.entity.NotificationEntity;

public interface NotificationService {
    NotificationEntity createNotification(NotificationInDTO dto);
}
