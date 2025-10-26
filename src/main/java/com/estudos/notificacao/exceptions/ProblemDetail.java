package com.estudos.notificacao.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Detalhes do erro seguindo o padrão RFC 9457")
public class ProblemDetail {

    @Schema(description = "Tipo do erro", example = "/errors/validation-failed")
    private String type;

    @Schema(description = "Título do erro", example = "Erro de Validação")
    private String title;

    @Schema(description = "Status HTTP", example = "400")
    private Integer status;

    @Schema(description = "Descrição detalhada do erro", example = "Falha na validação dos campos de entrada")
    private String detail;

    @Schema(description = "URI do recurso que causou o erro", example = "/api/usuarios")
    private String instance;

    @Schema(description = "Timestamp do erro")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;

    @Schema(description = "Erros de validação por campo")
    private Map<String, List<String>> errors;

    @Schema(description = "Código interno do erro com ID de rastreamento",
            example = "VALIDATION_001-9401ccfa-d8ba-4288-b350-b52125ffb794")
    private String errorCode;

    @Schema(description = "ID de rastreamento único para suporte",
            example = "9401ccfa-d8ba-4288-b350-b52125ffb794")
    private String traceId;
}