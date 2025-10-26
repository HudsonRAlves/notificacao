package com.estudos.notificacao.mappers;

import com.estudos.notificacao.domain.dtos.UserInDTO;
import com.estudos.notificacao.domain.dtos.UserOutDTO;
import com.estudos.notificacao.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
   componentModel = "spring"
)
public interface UserMapper {
   UserOutDTO toDTO(UserEntity entity);

   UserEntity toEntity(UserInDTO dto);

   void updateEntityFromDTO(UserInDTO dto, @MappingTarget UserEntity entity);
}
