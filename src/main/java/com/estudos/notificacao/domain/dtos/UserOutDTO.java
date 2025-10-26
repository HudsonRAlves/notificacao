package com.estudos.notificacao.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Entidade que representa o retorno de um usuário")
@Getter
@Setter
public class UserOutDTO {

   @Schema(description = "ID do usuário",example = "1")
   private Long id;

   @Schema(description = "Nome completo do usuário",example = "João Silva")
   private String name;

   @Schema(description = "Email do usuário",example = "joao@silva.com.br")
   private String email;

}
