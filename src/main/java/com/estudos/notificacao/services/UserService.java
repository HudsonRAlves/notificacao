package com.estudos.notificacao.services;

import com.estudos.notificacao.domain.dtos.UserInDTO;
import com.estudos.notificacao.domain.dtos.UserOutDTO;
import java.util.List;

public interface UserService {
   UserOutDTO save(UserInDTO userDTO);
   List<UserOutDTO> findAll();
   UserOutDTO findById(Long id);
   void delete(Long id);
   UserOutDTO update(Long id, UserInDTO userDTO);
   void sendUserCreatedNotification(Object user);
}
