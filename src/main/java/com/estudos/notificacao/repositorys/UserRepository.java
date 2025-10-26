package com.estudos.notificacao.repositorys;

import com.estudos.notificacao.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
