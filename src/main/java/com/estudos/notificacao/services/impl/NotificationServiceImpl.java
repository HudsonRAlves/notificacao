package com.estudos.notificacao.services.impl;

import com.estudos.notificacao.domain.dtos.NotificationInDTO;
import com.estudos.notificacao.domain.entity.NotificationEntity;
import com.estudos.notificacao.domain.entity.UserEntity;
import com.estudos.notificacao.repositorys.NotificationRepository;
import com.estudos.notificacao.repositorys.UserRepository;
import com.estudos.notificacao.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    public NotificationEntity createNotification(NotificationInDTO dto) {

        UserEntity user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        NotificationEntity notification = new NotificationEntity();
        notification.setMessage(dto.getMessage());
        notification.setUser(user);

        return notificationRepository.save(notification);
    }
}
