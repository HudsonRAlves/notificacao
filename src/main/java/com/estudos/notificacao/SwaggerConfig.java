package com.estudos.notificacao;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info apiInfo = new Info()
                .title("Meu Projeto API teste de notificações")
                .version("1.0")
                .description("Documentação da API para teste de notificações")
                .contact(new Contact()
                        .name("Hudson Alves")
                        .email("hudsonralves@gmail.com")
                );

        return new OpenAPI().info(apiInfo);
    }
}