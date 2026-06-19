package com.therapyconnect.platform.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configures the OpenAPI specification exposed by the platform.
 */
@Configuration
public class OpenApiConfiguration {

    @Value("${spring.application.name}")
    String applicationName;

    @Value("${documentation.application.description}")
    String applicationDescription;

    @Value("${documentation.application.version}")
    String applicationVersion;

    @Bean
    public OpenAPI therapyConnectPlatformOpenApi() {
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title(this.applicationName)
                        .description(this.applicationDescription)
                        .version(this.applicationVersion)
                        .contact(new Contact()
                                .name("TherapyConnect Support")
                                .email("support@therapyconnect.com")
                                .url("https://therapyconnect.com/support"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("TherapyConnect Platform Documentation")
                        .url("https://therapyconnect.wiki.github.io/docs"));

        openApi.servers(List.of(
                new Server()
                        .url("http://localhost:8080")
                        .description("Local Development Environment"),
                new Server()
                        .url("https://staging-api.therapyconnect.com")
                        .description("Staging Environment"),
                new Server()
                        .url("https://api.therapyconnect.com")
                        .description("Production Environment")
        ));

        return openApi;
    }
}
