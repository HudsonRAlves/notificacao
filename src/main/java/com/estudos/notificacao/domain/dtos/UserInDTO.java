package com.estudos.notificacao.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Generated;

@Schema(
   description = "Entidade que representa a entrada de um novo usuário"
)
public class UserInDTO {
   @Schema(
      description = "Nome completo do usuário",
      example = "João Silva"
   )
   @NotBlank
   @NotEmpty
   private String name;
   private String email;

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getEmail() {
      return this.email;
   }

   @Generated
   public void setName(final String name) {
      this.name = name;
   }

   @Generated
   public void setEmail(final String email) {
      this.email = email;
   }
}
