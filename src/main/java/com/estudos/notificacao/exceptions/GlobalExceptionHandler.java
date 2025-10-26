package com.estudos.notificacao.exceptions;

import com.estudos.notificacao.domain.dtos.ProblemDetail;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String VALIDATION_ERROR_TYPE = "/errors/validation-failed";
    private static final String INTERNAL_ERROR_TYPE = "/errors/internal-server-error";
    private static final String NOT_FOUND_TYPE = "/errors/not-found";
    private static final String CONFLICT_TYPE = "/errors/conflict";
    private static final String UNAUTHORIZED_TYPE = "/errors/unauthorized";
    private static final String FORBIDDEN_TYPE = "/errors/forbidden";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {

        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        String traceId = getTraceId();
        String correlationId = getCorrelationId();

        Map<String, List<String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        ProblemDetail problemDetail = createProblemDetail(
                VALIDATION_ERROR_TYPE,
                "Erro de Validação",
                HttpStatus.BAD_REQUEST.value(),
                "Falha na validação dos campos de entrada",
                servletRequest.getRequestURI(),
                traceId,
                "VALIDATION_001"
        );
        problemDetail.setErrors(errors);

        log.warn("Erro de validação - Endpoint: {} {}, Erros: {}",
                servletRequest.getMethod(),
                servletRequest.getRequestURI(),
                errors);

        return buildErrorResponse(problemDetail, traceId, correlationId);
    }

    @ExceptionHandler(org.springframework.web.bind.MissingServletRequestParameterException.class)
    public ResponseEntity<ProblemDetail> handleMissingParams(
            org.springframework.web.bind.MissingServletRequestParameterException ex, WebRequest request) {

        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        String traceId = getTraceId();
        String correlationId = getCorrelationId();

        ProblemDetail problemDetail = createProblemDetail(
                VALIDATION_ERROR_TYPE,
                "Parâmetro obrigatório não informado",
                HttpStatus.BAD_REQUEST.value(),
                String.format("O parâmetro '%s' é obrigatório", ex.getParameterName()),
                servletRequest.getRequestURI(),
                traceId,
                "VALIDATION_002"
        );

        log.warn("Parâmetro obrigatório faltando - Endpoint: {} {}, Parâmetro: {}",
                servletRequest.getMethod(),
                servletRequest.getRequestURI(),
                ex.getParameterName());

        return buildErrorResponse(problemDetail, traceId, correlationId);
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleMessageNotReadable(
            org.springframework.http.converter.HttpMessageNotReadableException ex, WebRequest request) {

        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        String traceId = getTraceId();
        String correlationId = getCorrelationId();

        ProblemDetail problemDetail = createProblemDetail(
                VALIDATION_ERROR_TYPE,
                "Corpo da requisição inválido",
                HttpStatus.BAD_REQUEST.value(),
                "O corpo da requisição não pôde ser processado. Verifique o formato dos dados.",
                servletRequest.getRequestURI(),
                traceId,
                "VALIDATION_003"
        );

        log.warn("Corpo da requisição inválido - Endpoint: {} {}, Content-Type: {}",
                servletRequest.getMethod(),
                servletRequest.getRequestURI(),
                servletRequest.getContentType());

        return buildErrorResponse(problemDetail, traceId, correlationId);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolation(
            jakarta.validation.ConstraintViolationException ex, WebRequest request) {

        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        String traceId = getTraceId();
        String correlationId = getCorrelationId();

        Map<String, List<String>> errors = ex.getConstraintViolations().stream()
                .collect(Collectors.groupingBy(
                        violation -> violation.getPropertyPath().toString(),
                        Collectors.mapping(violation -> violation.getMessage(), Collectors.toList())
                ));

        ProblemDetail problemDetail = createProblemDetail(
                VALIDATION_ERROR_TYPE,
                "Violação de Constraints",
                HttpStatus.BAD_REQUEST.value(),
                "Falha na validação das constraints",
                servletRequest.getRequestURI(),
                traceId,
                "VALIDATION_004"
        );
        problemDetail.setErrors(errors);

        log.warn("Violação de constraint - Endpoint: {} {}, Campos: {}",
                servletRequest.getMethod(),
                servletRequest.getRequestURI(),
                errors.keySet());

        return buildErrorResponse(problemDetail, traceId, correlationId);
    }

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public ResponseEntity<ProblemDetail> handleNoHandlerFound(
            org.springframework.web.servlet.NoHandlerFoundException ex, WebRequest request) {

        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        String traceId = getTraceId();
        String correlationId = getCorrelationId();

        ProblemDetail problemDetail = createProblemDetail(
                NOT_FOUND_TYPE,
                "Recurso não encontrado",
                HttpStatus.NOT_FOUND.value(),
                "O endpoint solicitado não foi encontrado",
                servletRequest.getRequestURI(),
                traceId,
                "NOT_FOUND_001"
        );

        log.warn("Recurso não encontrado - Endpoint: {} {}, Método HTTP: {}",
                servletRequest.getMethod(),
                servletRequest.getRequestURI(),
                ex.getHttpMethod());

        return buildErrorResponse(problemDetail, traceId, correlationId);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleDataIntegrityViolation(
            org.springframework.dao.DataIntegrityViolationException ex, WebRequest request) {

        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        String traceId = getTraceId();
        String correlationId = getCorrelationId();

        ProblemDetail problemDetail = createProblemDetail(
                CONFLICT_TYPE,
                "Conflito de dados",
                HttpStatus.CONFLICT.value(),
                "Os dados fornecidos conflitam com registros existentes",
                servletRequest.getRequestURI(),
                traceId,
                "CONFLICT_001"
        );

        log.warn("Conflito de dados - Endpoint: {} {}, Mensagem: {}",
                servletRequest.getMethod(),
                servletRequest.getRequestURI(),
                ex.getMostSpecificCause().getMessage());

        return buildErrorResponse(problemDetail, traceId, correlationId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {

        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        String traceId = getTraceId();
        String correlationId = getCorrelationId();

        ProblemDetail problemDetail = createProblemDetail(
                VALIDATION_ERROR_TYPE,
                "Argumento inválido",
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                servletRequest.getRequestURI(),
                traceId,
                "VALIDATION_005"
        );

        log.warn("Argumento inválido - Endpoint: {} {}, Mensagem: {}",
                servletRequest.getMethod(),
                servletRequest.getRequestURI(),
                ex.getMessage());

        return buildErrorResponse(problemDetail, traceId, correlationId);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ProblemDetail> handleIllegalState(
            IllegalStateException ex, WebRequest request) {

        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        String traceId = getTraceId();
        String correlationId = getCorrelationId();

        ProblemDetail problemDetail = createProblemDetail(
                CONFLICT_TYPE,
                "Estado inválido",
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                servletRequest.getRequestURI(),
                traceId,
                "CONFLICT_002"
        );

        log.warn("Estado inválido - Endpoint: {} {}, Mensagem: {}",
                servletRequest.getMethod(),
                servletRequest.getRequestURI(),
                ex.getMessage());

        return buildErrorResponse(problemDetail, traceId, correlationId);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(Exception ex, WebRequest request) {

        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        String traceId = getTraceId();
        String correlationId = getCorrelationId();

        ProblemDetail problemDetail = createProblemDetail(
                INTERNAL_ERROR_TYPE,
                "Erro Interno do Servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno no servidor. Consulte o suporte com o Trace ID.",
                servletRequest.getRequestURI(),
                traceId,
                "INTERNAL_001"
        );

        log.error("Erro interno do servidor - Endpoint: {} {}, Exceção: {}, Mensagem: {}",
                servletRequest.getMethod(),
                servletRequest.getRequestURI(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                ex);

        return buildErrorResponse(problemDetail, traceId, correlationId);
    }

    // ========== MÉTODOS AUXILIARES ==========

    private ProblemDetail createProblemDetail(String type, String title, int status,
                                              String detail, String instance,
                                              String traceId, String errorCode) {
        ProblemDetail problemDetail = new ProblemDetail();
        problemDetail.setType(type);
        problemDetail.setTitle(title);
        problemDetail.setStatus(status);
        problemDetail.setDetail(detail);
        problemDetail.setInstance(instance);
        problemDetail.setTimestamp(LocalDateTime.now());
        problemDetail.setTraceId(traceId);
        problemDetail.setErrorCode(errorCode + "-" + traceId);
        return problemDetail;
    }

    private ResponseEntity<ProblemDetail> buildErrorResponse(ProblemDetail problemDetail,
                                                             String traceId, String correlationId) {
        return ResponseEntity.status(problemDetail.getStatus())
                .header("X-Trace-ID", traceId)
                .header("X-Correlation-ID", correlationId)
                .body(problemDetail);
    }

    private String getTraceId() {
        String traceId = MDC.get("traceId");
        return traceId != null ? traceId : UUID.randomUUID().toString();
    }

    private String getCorrelationId() {
        String correlationId = MDC.get("correlationId");
        return correlationId != null ? correlationId : UUID.randomUUID().toString();
    }
}