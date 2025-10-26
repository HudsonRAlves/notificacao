package com.estudos.notificacao.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Entidade que representa a entrada de um novo usuário")
@Getter
@Setter
public class UserInDTO {

   @Schema(description = "Nome completo do usuário",example = "João Silva")
   @NotBlank
   @NotEmpty
   private String name;

   @Schema(description = "Email do usuário",example = "joao@silva.com.br")
   private String email;

}
