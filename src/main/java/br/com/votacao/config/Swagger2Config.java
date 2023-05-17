package br.com.votacao.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
		title = "Spring Boot REST API Documentation",
		description = "Spring Boot REST API Documentation",
		version = "V1.0",
		contact = @Contact(
				name = "Alessandro Borges de Souza",
				email = "alessandro.souza@edu.pucrs.br",
				url = "https://github.com/AleYasSusu/"
		),
		license = @License(
				name = "Apache 2.0",
				url = ""
		)
)
)
public class Swagger2Config {

}