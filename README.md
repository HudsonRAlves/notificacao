# 📢 Notificação - Sistema de Notificações em Tempo Real com Spring Boot

# Dependências Principais <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" width="30" alt="Spring"/>

## Core Spring Boot ⚡
- 🌐 `spring-boot-starter-web` – Framework web para APIs REST
- 🔌 `spring-boot-starter-websocket` – Suporte a comunicação em tempo real via WebSocket/STOMP
- ✅ `spring-boot-starter-validation` – Validação de dados com Bean Validation
- 🗃️ `spring-boot-starter-data-jpa` – Persistência de dados com JPA/Hibernate

## Mensageria 📨
- ✉️ `spring-boot-starter-messaging` – Suporte a mensagens STOMP para WebSocket

## Banco de Dados 🗄️
- 🔄 `liquibase-core` – Versionamento e migração de esquema de banco de dados
- 🔧 Driver do banco (`postgresql-driver`)

## Documentação 📚
- 📖 `springdoc-openapi-starter-webmvc-ui` – Documentação automática da API com Swagger/OpenAPI

## Mapeamento de Objetos 🔀
- 🗺️ `mapstruct` – Mapeamento automático entre DTOs e Entidades
- ⚙️ `mapstruct-processor` – Processador de anotações do MapStruct

## Testes 🧪
- 🧪 `spring-boot-starter-test` – Framework de testes (JUnit, Mockito, etc.)

## Outras 🛠️
- ✂️ `lombok` (opcional) – Redução de código boilerplate

> **Observação** ℹ️: O projeto utiliza **WebSocket com STOMP** para notificações em tempo real, conforme configurado em `WebSocketConfig.java`, com endpoints em `/ws` e prefixos `/app` e `/topic`.
