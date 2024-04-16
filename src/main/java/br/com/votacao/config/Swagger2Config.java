package br.com.votacao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class Swagger2Config {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API votação")
                        .description("API para criar votações e computar votos para pautas")
                        .contact(new Contact().name("Alesandro Borges de Souza").email("alessandro.souza@edu.pucrs.br")
                        ).version("1.0.0"));
    }
}