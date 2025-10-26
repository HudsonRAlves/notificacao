package com.estudos.notificacao.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonInclude(Include.NON_NULL)
@Schema(
   description = "Detalhes do erro seguindo o padrão RFC 9457"
)
public class ProblemDetail {
   @Schema(
      description = "Tipo do erro",
      example = "/errors/validation-failed"
   )
   private String type;
   @Schema(
      description = "Título do erro",
      example = "Erro de Validação"
   )
   private String title;
   @Schema(
      description = "Status HTTP",
      example = "400"
   )
   private Integer status;
   @Schema(
      description = "Descrição detalhada do erro",
      example = "Falha na validação dos campos de entrada"
   )
   private String detail;
   @Schema(
      description = "URI do recurso que causou o erro",
      example = "/api/usuarios"
   )
   private String instance;
   @Schema(
      description = "Timestamp do erro"
   )
   @JsonFormat(
      pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
   )
   private LocalDateTime timestamp;
   @Schema(
      description = "Erros de validação por campo"
   )
   private Map<String, List<String>> errors;
   @Schema(
      description = "Código interno do erro com ID de rastreamento",
      example = "VALIDATION_001-9401ccfa-d8ba-4288-b350-b52125ffb794"
   )
   private String errorCode;
   @Schema(
      description = "ID de rastreamento único para suporte",
      example = "9401ccfa-d8ba-4288-b350-b52125ffb794"
   )
   private String traceId;

   @Generated
   public String getType() {
      return this.type;
   }

   @Generated
   public String getTitle() {
      return this.title;
   }

   @Generated
   public Integer getStatus() {
      return this.status;
   }

   @Generated
   public String getDetail() {
      return this.detail;
   }

   @Generated
   public String getInstance() {
      return this.instance;
   }

   @Generated
   public LocalDateTime getTimestamp() {
      return this.timestamp;
   }

   @Generated
   public Map<String, List<String>> getErrors() {
      return this.errors;
   }

   @Generated
   public String getErrorCode() {
      return this.errorCode;
   }

   @Generated
   public String getTraceId() {
      return this.traceId;
   }

   @Generated
   public void setType(final String type) {
      this.type = type;
   }

   @Generated
   public void setTitle(final String title) {
      this.title = title;
   }

   @Generated
   public void setStatus(final Integer status) {
      this.status = status;
   }

   @Generated
   public void setDetail(final String detail) {
      this.detail = detail;
   }

   @Generated
   public void setInstance(final String instance) {
      this.instance = instance;
   }

   @Generated
   public void setTimestamp(final LocalDateTime timestamp) {
      this.timestamp = timestamp;
   }

   @Generated
   public void setErrors(final Map<String, List<String>> errors) {
      this.errors = errors;
   }

   @Generated
   public void setErrorCode(final String errorCode) {
      this.errorCode = errorCode;
   }

   @Generated
   public void setTraceId(final String traceId) {
      this.traceId = traceId;
   }

   @Generated
   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ProblemDetail)) {
         return false;
      } else {
         ProblemDetail other = (ProblemDetail)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label119: {
               Object this$status = this.getStatus();
               Object other$status = other.getStatus();
               if (this$status == null) {
                  if (other$status == null) {
                     break label119;
                  }
               } else if (this$status.equals(other$status)) {
                  break label119;
               }

               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            label105: {
               Object this$title = this.getTitle();
               Object other$title = other.getTitle();
               if (this$title == null) {
                  if (other$title == null) {
                     break label105;
                  }
               } else if (this$title.equals(other$title)) {
                  break label105;
               }

               return false;
            }

            Object this$detail = this.getDetail();
            Object other$detail = other.getDetail();
            if (this$detail == null) {
               if (other$detail != null) {
                  return false;
               }
            } else if (!this$detail.equals(other$detail)) {
               return false;
            }

            label91: {
               Object this$instance = this.getInstance();
               Object other$instance = other.getInstance();
               if (this$instance == null) {
                  if (other$instance == null) {
                     break label91;
                  }
               } else if (this$instance.equals(other$instance)) {
                  break label91;
               }

               return false;
            }

            Object this$timestamp = this.getTimestamp();
            Object other$timestamp = other.getTimestamp();
            if (this$timestamp == null) {
               if (other$timestamp != null) {
                  return false;
               }
            } else if (!this$timestamp.equals(other$timestamp)) {
               return false;
            }

            label77: {
               Object this$errors = this.getErrors();
               Object other$errors = other.getErrors();
               if (this$errors == null) {
                  if (other$errors == null) {
                     break label77;
                  }
               } else if (this$errors.equals(other$errors)) {
                  break label77;
               }

               return false;
            }

            label70: {
               Object this$errorCode = this.getErrorCode();
               Object other$errorCode = other.getErrorCode();
               if (this$errorCode == null) {
                  if (other$errorCode == null) {
                     break label70;
                  }
               } else if (this$errorCode.equals(other$errorCode)) {
                  break label70;
               }

               return false;
            }

            Object this$traceId = this.getTraceId();
            Object other$traceId = other.getTraceId();
            if (this$traceId == null) {
               if (other$traceId != null) {
                  return false;
               }
            } else if (!this$traceId.equals(other$traceId)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(final Object other) {
      return other instanceof ProblemDetail;
   }

   @Generated
   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $status = this.getStatus();
      int result = result * 59 + ($status == null ? 43 : $status.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $title = this.getTitle();
      result = result * 59 + ($title == null ? 43 : $title.hashCode());
      Object $detail = this.getDetail();
      result = result * 59 + ($detail == null ? 43 : $detail.hashCode());
      Object $instance = this.getInstance();
      result = result * 59 + ($instance == null ? 43 : $instance.hashCode());
      Object $timestamp = this.getTimestamp();
      result = result * 59 + ($timestamp == null ? 43 : $timestamp.hashCode());
      Object $errors = this.getErrors();
      result = result * 59 + ($errors == null ? 43 : $errors.hashCode());
      Object $errorCode = this.getErrorCode();
      result = result * 59 + ($errorCode == null ? 43 : $errorCode.hashCode());
      Object $traceId = this.getTraceId();
      result = result * 59 + ($traceId == null ? 43 : $traceId.hashCode());
      return result;
   }

   @Generated
   public String toString() {
      String var10000 = this.getType();
      return "ProblemDetail(type=" + var10000 + ", title=" + this.getTitle() + ", status=" + this.getStatus() + ", detail=" + this.getDetail() + ", instance=" + this.getInstance() + ", timestamp=" + String.valueOf(this.getTimestamp()) + ", errors=" + String.valueOf(this.getErrors()) + ", errorCode=" + this.getErrorCode() + ", traceId=" + this.getTraceId() + ")";
   }

   @Generated
   public ProblemDetail() {
   }

   @Generated
   public ProblemDetail(final String type, final String title, final Integer status, final String detail, final String instance, final LocalDateTime timestamp, final Map<String, List<String>> errors, final String errorCode, final String traceId) {
      this.type = type;
      this.title = title;
      this.status = status;
      this.detail = detail;
      this.instance = instance;
      this.timestamp = timestamp;
      this.errors = errors;
      this.errorCode = errorCode;
      this.traceId = traceId;
   }
}
