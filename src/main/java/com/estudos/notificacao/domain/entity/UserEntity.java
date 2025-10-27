package com.estudos.notificacao.domain.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq")
   @SequenceGenerator(name = "user_seq",sequenceName = "user_seq",allocationSize = 1)
   private Long id;

   @Column(name = "name",nullable = false)
   private String name;

   @Column(name = "email",nullable = false)
   private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationEntity> notificacoes = new ArrayList<>();


}
