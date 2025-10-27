package com.estudos.notificacao.services.impl;

import com.estudos.notificacao.domain.dtos.UserInDTO;
import com.estudos.notificacao.domain.dtos.UserOutDTO;
import com.estudos.notificacao.domain.entity.UserEntity;
import com.estudos.notificacao.mappers.UserMapper;
import com.estudos.notificacao.repositorys.UserRepository;
import com.estudos.notificacao.services.UserService;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public UserOutDTO save(UserInDTO userDTO) {
        UserEntity user = this.userMapper.toEntity(userDTO);
        UserEntity savedUser = this.userRepository.save(user);
        sendUserCreatedNotification("Novo usuário criado: " + savedUser.getName());
        return this.userMapper.toDTO(savedUser);
    }

    @Override
    public List<UserOutDTO> findAll() {
        return this.userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserOutDTO findById(Long id) {
        return this.userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {

        UserEntity existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

        this.userRepository.deleteById(id);

        sendUserCreatedNotification("Usuário removido: " + existingUser.getName());
    }

    @Override
    public UserOutDTO update(Long id, UserInDTO userDTO) {
        UserEntity existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

        this.userMapper.updateEntityFromDTO(userDTO, existingUser);
        UserEntity updatedUser = this.userRepository.save(existingUser);
        sendUserCreatedNotification("Usuário atualizado: " + updatedUser.getName());
        return this.userMapper.toDTO(updatedUser);
    }

    @Override
    public void sendUserCreatedNotification(Object user) {
        messagingTemplate.convertAndSend("/topic/user", user);
    }

}