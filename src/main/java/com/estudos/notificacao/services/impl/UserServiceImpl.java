package com.estudos.notificacao.services.impl;

import com.estudos.notificacao.domain.dtos.UserInDTO;
import com.estudos.notificacao.domain.dtos.UserOutDTO;
import com.estudos.notificacao.domain.entity.UserEntity;
import com.estudos.notificacao.mappers.UserMapper;
import com.estudos.notificacao.repositorys.UserRepository;
import com.estudos.notificacao.services.UserService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserOutDTO save(UserInDTO userDTO) {
        UserEntity user = this.userMapper.toEntity(userDTO);
        UserEntity savedUser = this.userRepository.save(user);
        return this.userMapper.toDTO(savedUser);
    }

    public List<UserOutDTO> findAll() {
        return this.userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserOutDTO findById(Long id) {
        return this.userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElse(null);
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    public UserOutDTO update(Long id, UserInDTO userDTO) {
        UserEntity existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

        this.userMapper.updateEntityFromDTO(userDTO, existingUser);
        UserEntity updatedUser = this.userRepository.save(existingUser);
        return this.userMapper.toDTO(updatedUser);
    }

}