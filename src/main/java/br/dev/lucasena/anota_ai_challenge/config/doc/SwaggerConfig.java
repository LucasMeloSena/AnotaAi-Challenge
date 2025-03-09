package br.dev.lucasena.anota_ai_challenge.config.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AnotaAi Challenge")
                        .version("1.0.0")
                        .description("Documentação da API com Spring Boot e Swagger"));
    }
}
