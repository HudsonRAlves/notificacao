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
@Table(
   name = "users"
)
public class UserEntity {
   @Id
   @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "user_seq"
   )
   @SequenceGenerator(
      name = "user_seq",
      sequenceName = "user_seq",
      allocationSize = 1
   )
   private Long id;
   @Column(
      name = "name",
      nullable = false
   )
   private String name;
   @Column(
      name = "email",
      nullable = false
   )
   private String email;

   @Generated
   public Long getId() {
      return this.id;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getEmail() {
      return this.email;
   }

   @Generated
   public void setId(final Long id) {
      this.id = id;
   }

   @Generated
   public void setName(final String name) {
      this.name = name;
   }

   @Generated
   public void setEmail(final String email) {
      this.email = email;
   }
}
