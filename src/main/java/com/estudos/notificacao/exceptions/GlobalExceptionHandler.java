package com.estudos.notificacao.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @Generated
   private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
   private static final String VALIDATION_ERROR_TYPE = "/errors/validation-failed";
   private static final String INTERNAL_ERROR_TYPE = "/errors/internal-server-error";
   private static final String NOT_FOUND_TYPE = "/errors/not-found";
   private static final String CONFLICT_TYPE = "/errors/conflict";
   private static final String UNAUTHORIZED_TYPE = "/errors/unauthorized";
   private static final String FORBIDDEN_TYPE = "/errors/forbidden";

   @ExceptionHandler({MethodArgumentNotValidException.class})
   public ResponseEntity<ProblemDetail> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
      HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
      String traceId = this.getTraceId();
      String correlationId = this.getCorrelationId();
      Map<String, List<String>> errors = (Map)ex.getBindingResult().getFieldErrors().stream().collect(Collectors.groupingBy(FieldError::getField, Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toList())));
      ProblemDetail problemDetail = this.createProblemDetail("/errors/validation-failed", "Erro de Validação", HttpStatus.BAD_REQUEST.value(), "Falha na validação dos campos de entrada", servletRequest.getRequestURI(), traceId, "VALIDATION_001");
      problemDetail.setErrors(errors);
      log.warn("Erro de validação - Endpoint: {} {}, Erros: {}", new Object[]{servletRequest.getMethod(), servletRequest.getRequestURI(), errors});
      return this.buildErrorResponse(problemDetail, traceId, correlationId);
   }

   @ExceptionHandler({MissingServletRequestParameterException.class})
   public ResponseEntity<ProblemDetail> handleMissingParams(MissingServletRequestParameterException ex, WebRequest request) {
      HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
      String traceId = this.getTraceId();
      String correlationId = this.getCorrelationId();
      ProblemDetail problemDetail = this.createProblemDetail("/errors/validation-failed", "Parâmetro obrigatório não informado", HttpStatus.BAD_REQUEST.value(), String.format("O parâmetro '%s' é obrigatório", ex.getParameterName()), servletRequest.getRequestURI(), traceId, "VALIDATION_002");
      log.warn("Parâmetro obrigatório faltando - Endpoint: {} {}, Parâmetro: {}", new Object[]{servletRequest.getMethod(), servletRequest.getRequestURI(), ex.getParameterName()});
      return this.buildErrorResponse(problemDetail, traceId, correlationId);
   }

   @ExceptionHandler({HttpMessageNotReadableException.class})
   public ResponseEntity<ProblemDetail> handleMessageNotReadable(HttpMessageNotReadableException ex, WebRequest request) {
      HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
      String traceId = this.getTraceId();
      String correlationId = this.getCorrelationId();
      ProblemDetail problemDetail = this.createProblemDetail("/errors/validation-failed", "Corpo da requisição inválido", HttpStatus.BAD_REQUEST.value(), "O corpo da requisição não pôde ser processado. Verifique o formato dos dados.", servletRequest.getRequestURI(), traceId, "VALIDATION_003");
      log.warn("Corpo da requisição inválido - Endpoint: {} {}, Content-Type: {}", new Object[]{servletRequest.getMethod(), servletRequest.getRequestURI(), servletRequest.getContentType()});
      return this.buildErrorResponse(problemDetail, traceId, correlationId);
   }

   @ExceptionHandler({ConstraintViolationException.class})
   public ResponseEntity<ProblemDetail> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
      HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
      String traceId = this.getTraceId();
      String correlationId = this.getCorrelationId();
      Map<String, List<String>> errors = (Map)ex.getConstraintViolations().stream().collect(Collectors.groupingBy((violation) -> {
         return violation.getPropertyPath().toString();
      }, Collectors.mapping((violation) -> {
         return violation.getMessage();
      }, Collectors.toList())));
      ProblemDetail problemDetail = this.createProblemDetail("/errors/validation-failed", "Violação de Constraints", HttpStatus.BAD_REQUEST.value(), "Falha na validação das constraints", servletRequest.getRequestURI(), traceId, "VALIDATION_004");
      problemDetail.setErrors(errors);
      log.warn("Violação de constraint - Endpoint: {} {}, Campos: {}", new Object[]{servletRequest.getMethod(), servletRequest.getRequestURI(), errors.keySet()});
      return this.buildErrorResponse(problemDetail, traceId, correlationId);
   }

   @ExceptionHandler({NoHandlerFoundException.class})
   public ResponseEntity<ProblemDetail> handleNoHandlerFound(NoHandlerFoundException ex, WebRequest request) {
      HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
      String traceId = this.getTraceId();
      String correlationId = this.getCorrelationId();
      ProblemDetail problemDetail = this.createProblemDetail("/errors/not-found", "Recurso não encontrado", HttpStatus.NOT_FOUND.value(), "O endpoint solicitado não foi encontrado", servletRequest.getRequestURI(), traceId, "NOT_FOUND_001");
      log.warn("Recurso não encontrado - Endpoint: {} {}, Método HTTP: {}", new Object[]{servletRequest.getMethod(), servletRequest.getRequestURI(), ex.getHttpMethod()});
      return this.buildErrorResponse(problemDetail, traceId, correlationId);
   }

   @ExceptionHandler({DataIntegrityViolationException.class})
   public ResponseEntity<ProblemDetail> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
      HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
      String traceId = this.getTraceId();
      String correlationId = this.getCorrelationId();
      ProblemDetail problemDetail = this.createProblemDetail("/errors/conflict", "Conflito de dados", HttpStatus.CONFLICT.value(), "Os dados fornecidos conflitam com registros existentes", servletRequest.getRequestURI(), traceId, "CONFLICT_001");
      log.warn("Conflito de dados - Endpoint: {} {}, Mensagem: {}", new Object[]{servletRequest.getMethod(), servletRequest.getRequestURI(), ex.getMostSpecificCause().getMessage()});
      return this.buildErrorResponse(problemDetail, traceId, correlationId);
   }

   @ExceptionHandler({IllegalArgumentException.class})
   public ResponseEntity<ProblemDetail> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
      HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
      String traceId = this.getTraceId();
      String correlationId = this.getCorrelationId();
      ProblemDetail problemDetail = this.createProblemDetail("/errors/validation-failed", "Argumento inválido", HttpStatus.BAD_REQUEST.value(), ex.getMessage(), servletRequest.getRequestURI(), traceId, "VALIDATION_005");
      log.warn("Argumento inválido - Endpoint: {} {}, Mensagem: {}", new Object[]{servletRequest.getMethod(), servletRequest.getRequestURI(), ex.getMessage()});
      return this.buildErrorResponse(problemDetail, traceId, correlationId);
   }

   @ExceptionHandler({IllegalStateException.class})
   public ResponseEntity<ProblemDetail> handleIllegalState(IllegalStateException ex, WebRequest request) {
      HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
      String traceId = this.getTraceId();
      String correlationId = this.getCorrelationId();
      ProblemDetail problemDetail = this.createProblemDetail("/errors/conflict", "Estado inválido", HttpStatus.CONFLICT.value(), ex.getMessage(), servletRequest.getRequestURI(), traceId, "CONFLICT_002");
      log.warn("Estado inválido - Endpoint: {} {}, Mensagem: {}", new Object[]{servletRequest.getMethod(), servletRequest.getRequestURI(), ex.getMessage()});
      return this.buildErrorResponse(problemDetail, traceId, correlationId);
   }

   @ExceptionHandler({Exception.class})
   public ResponseEntity<ProblemDetail> handleGenericException(Exception ex, WebRequest request) {
      HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
      String traceId = this.getTraceId();
      String correlationId = this.getCorrelationId();
      ProblemDetail problemDetail = this.createProblemDetail("/errors/internal-server-error", "Erro Interno do Servidor", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro interno no servidor. Consulte o suporte com o Trace ID.", servletRequest.getRequestURI(), traceId, "INTERNAL_001");
      log.error("Erro interno do servidor - Endpoint: {} {}, Exceção: {}, Mensagem: {}", new Object[]{servletRequest.getMethod(), servletRequest.getRequestURI(), ex.getClass().getSimpleName(), ex.getMessage(), ex});
      return this.buildErrorResponse(problemDetail, traceId, correlationId);
   }

   private ProblemDetail createProblemDetail(String type, String title, int status, String detail, String instance, String traceId, String errorCode) {
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

   private ResponseEntity<ProblemDetail> buildErrorResponse(ProblemDetail problemDetail, String traceId, String correlationId) {
      return ((BodyBuilder)((BodyBuilder)ResponseEntity.status(problemDetail.getStatus()).header("X-Trace-ID", new String[]{traceId})).header("X-Correlation-ID", new String[]{correlationId})).body(problemDetail);
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
