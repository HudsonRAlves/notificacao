package com.estudos.notificacao.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;

@Schema(
   description = "Entidade que representa o retorno de um usuário"
)
public class UserOutDTO {
   @Schema(
      description = "ID do usuário",
      example = "1"
   )
   private Long id;
   @Schema(
      description = "Nome completo do usuário",
      example = "João Silva"
   )
   private String name;
   @Schema(
      description = "Email do usuário",
      example = "joao@silva.com.br"
   )
   private String email;

   @Generated
   public Long getId() {
      return this.id;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getEmail() {
      return this.email;
   }

   @Generated
   public void setId(final Long id) {
      this.id = id;
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
