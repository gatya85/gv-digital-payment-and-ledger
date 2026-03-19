package com.gv.dpal.fraud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI fraudServiceAPI(){
        return new OpenAPI().info(new Info().title("Fraud Service API")
                        .description("This is the REST API for Fraud Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Fraud Service Wiki Documentation")
                        .url("https://fraud-service-dummy-url.com/docs"));

    }
}
