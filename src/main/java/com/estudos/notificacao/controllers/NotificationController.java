package com.estudos.notificacao.controllers;

import com.estudos.notificacao.domain.dtos.NotificationInDTO;
import com.estudos.notificacao.domain.entity.NotificationEntity;
import com.estudos.notificacao.services.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Notificações", description = "Operações relacionadas a notificações")
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    @Operation(summary = "Cria uma nova notificação", description = "Adiciona uma notificação para um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<NotificationEntity> create(@RequestBody NotificationInDTO dto) {
        NotificationEntity notification = notificationService.createNotification(dto);
        return ResponseEntity.ok(notification);
    }
}