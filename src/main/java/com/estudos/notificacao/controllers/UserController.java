package com.estudos.notificacao.controllers;

import com.estudos.notificacao.domain.dtos.UserInDTO;
import com.estudos.notificacao.domain.dtos.UserOutDTO;
import com.estudos.notificacao.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/users"})
@Tag(
   name = "Usuários",
   description = "Operações para gerenciamento de usuários"
)
public class UserController {
   private final UserService service;

   public UserController(UserService service) {
      this.service = service;
   }

   @GetMapping
   @Operation(
      summary = "Listar todos os usuários",
      description = "Retorna uma lista com todos os usuários cadastrados"
   )
   @ApiResponse(
      responseCode = "200",
      description = "Usuários listados com sucesso"
   )
   public List<UserOutDTO> getAll() {
      return this.service.findAll();
   }

   @PostMapping
   @Operation(
      summary = "Criar novo usuário"
   )
   @ApiResponses({@ApiResponse(
   responseCode = "201",
   description = "Usuário criado com sucesso"
), @ApiResponse(
   responseCode = "400",
   description = "Dados inválidos"
)})
   public UserOutDTO create(@RequestBody @Valid UserInDTO userDTO) {
      return this.service.save(userDTO);
   }

   @GetMapping({"/{id}"})
   @Operation(
      summary = "Buscar usuário por ID"
   )
   @ApiResponses({@ApiResponse(
   responseCode = "200",
   description = "Usuário encontrado"
), @ApiResponse(
   responseCode = "404",
   description = "Usuário não encontrado"
)})
   public UserOutDTO getById(@PathVariable Long id) {
      return this.service.findById(id);
   }

   @DeleteMapping({"/{id}"})
   @Operation(
      summary = "Excluir usuário"
   )
   @ApiResponses({@ApiResponse(
   responseCode = "204",
   description = "Usuário excluído com sucesso"
), @ApiResponse(
   responseCode = "404",
   description = "Usuário não encontrado"
)})
   public void delete(@PathVariable Long id) {
      this.service.delete(id);
   }

   @PutMapping({"/{id}"})
   @Operation(
      summary = "Atualizar usuário"
   )
   @ApiResponses({@ApiResponse(
   responseCode = "200",
   description = "Usuário atualizado com sucesso"
), @ApiResponse(
   responseCode = "400",
   description = "Dados inválidos"
), @ApiResponse(
   responseCode = "404",
   description = "Usuário não encontrado"
)})
   public UserOutDTO update(@PathVariable Long id, @RequestBody @Valid UserInDTO userDTO) {
      return this.service.update(id, userDTO);
   }
}
