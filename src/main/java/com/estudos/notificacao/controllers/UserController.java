package com.estudos.notificacao.controllers;

import com.estudos.notificacao.domain.dtos.UserInDTO;
import com.estudos.notificacao.domain.dtos.UserOutDTO;
import com.estudos.notificacao.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/users"})
@Tag(name = "Usuários",description = "Operações para gerenciamento de usuários")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados")
    @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso")
    public List<UserOutDTO> getAll() {
        return this.service.findAll();
    }

    @PostMapping
    @Operation(summary = "Criar novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = UserOutDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")

    })
    public UserOutDTO create(@RequestBody @Valid UserInDTO userDTO) {
        return this.service.save(userDTO);
    }

    @GetMapping({"/{id}"})
    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(schema = @Schema(implementation = UserOutDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public UserOutDTO getById(@PathVariable Long id) {
        return this.service.findById(id);
    }

    @DeleteMapping({"/{id}"})
    @Operation(summary = "Excluir usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"
    )})
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }

    @PutMapping({"/{id}"})
    @Operation(summary = "Atualizar usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = UserOutDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"
    )})
    public UserOutDTO update(@PathVariable Long id, @RequestBody @Valid UserInDTO userDTO) {
        return this.service.update(id, userDTO);
    }

}
