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
import org.springframework.stereotype.Service;

@Service
public class UserImplService implements UserService {
   private final UserRepository repository;
   private final UserMapper mapper;

   public UserOutDTO save(UserInDTO userDTO) {
      UserEntity user = this.mapper.toEntity(userDTO);
      return this.mapper.toDTO((UserEntity)this.repository.save(user));
   }

   public List<UserOutDTO> findAll() {
      Stream var10000 = this.repository.findAll().stream();
      UserMapper var10001 = this.mapper;
      Objects.requireNonNull(var10001);
      return (List)var10000.map(var10001::toDTO).collect(Collectors.toList());
   }

   public UserOutDTO findById(Long id) {
      Optional var10000 = this.repository.findById(id);
      UserMapper var10001 = this.mapper;
      Objects.requireNonNull(var10001);
      return (UserOutDTO)var10000.map(var10001::toDTO).orElse((Object)null);
   }

   public void delete(Long id) {
      this.repository.deleteById(id);
   }

   public UserOutDTO update(Long id, UserInDTO userDTO) {
      return (UserOutDTO)this.repository.findById(id).map((existingUser) -> {
         this.mapper.updateEntityFromDTO(userDTO, existingUser);
         UserEntity updatedUser = (UserEntity)this.repository.save(existingUser);
         return this.mapper.toDTO(updatedUser);
      }).orElse((Object)null);
   }

   @Generated
   public UserImplService(final UserRepository repository, final UserMapper mapper) {
      this.repository = repository;
      this.mapper = mapper;
   }
}
