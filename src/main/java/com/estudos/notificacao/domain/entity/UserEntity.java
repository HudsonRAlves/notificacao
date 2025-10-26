package com.estudos.notificacao.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Generated;

@Entity
@Table(name = "users")
public class UserEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq")
   @SequenceGenerator(name = "user_seq",sequenceName = "user_seq",allocationSize = 1)
   private Long id;
   @Column(name = "name",nullable = false)
   private String name;

   @Column(name = "email",nullable = false)
   private String email;
}
