package com.ylurexon.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ylurexonOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Y-Lurexon API")
                                .description(
                                        "REST API for the Y-Lurexon watch platform"
                                )
                                .version("1.0")
                )
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("bearerAuth")
                )
                .schemaRequirement(
                        "bearerAuth",
                        new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );
    }
}