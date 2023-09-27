package br.com.waldir.userserviceapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${springdoc.openapi.title}") String title,
            @Value("${springdoc.openapi.description}") String description,
            @Value("${springdoc.openapi.version}") String version
    ){
        return new OpenAPI()
                .info(
                  new Info()
                          .title(title)
                          .description(description)
                          .version(version)
                );
    }

}
