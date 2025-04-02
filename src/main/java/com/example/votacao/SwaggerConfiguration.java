package com.example.votacao;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

        @Bean
        public OpenAPI apiInfo() {
                return new OpenAPI()
                                .info(
                                                new Info()
                                                                .title("Desafio Votação API")
                                                                .description("")
                                                                .version("1.0.0")
                                                                .license(new License())
                                                                .termsOfService(""));
        }
}
