package com.gv.dpal.ledger.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI ledgerServiceAPI(){
        return new OpenAPI().info(new Info().title("Ledger Service API")
                        .description("This is the REST API for Ledger Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Ledger Service Wiki Documentation")
                        .url("https://ledger-service-dummy-url.com/docs"));

    }
}
