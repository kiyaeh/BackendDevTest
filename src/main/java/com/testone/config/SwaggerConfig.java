package com.testone.config;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot REST API Documentation")
                        .description("API documentation for managing users and encrypted bank information")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Ewnetu Tesfaye")
                                .email("ewnkiya9420@gmail.com")
                                .url("https://ewnetutesfaye.netlify.app/"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}