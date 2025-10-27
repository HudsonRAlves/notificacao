package com.estudos.notificacao.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO de entrada para notificação")
public class NotificationInDTO {

    @Schema(description = "Mensagem da notificação", example = "Você tem uma nova mensagem")
    private String message;

    @Schema(description = "Id do usuário relacionado à notificação")
    private Long idUser;

}