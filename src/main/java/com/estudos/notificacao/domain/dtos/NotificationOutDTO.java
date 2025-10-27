package com.estudos.notificacao.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "DTO de saída para notificação")
public class NotificationOutDTO {

    @Schema(description = "ID da notificação", example = "1")
    private Long id;

    @Schema(description = "Mensagem da notificação", example = "Você tem uma nova mensagem")
    private String message;

    @Schema(description = "Indica se a notificação foi lida", example = "false")
    private boolean read = false;

    @Schema(description = "Usuário relacionado à notificação")
    private UserOutDTO user;

}