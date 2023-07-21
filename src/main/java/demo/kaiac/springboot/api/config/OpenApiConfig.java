package demo.kaiac.springboot.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Value("${demo.kaiac.openapi.app-url}")
    private String appUrl;

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {

        Server appServer = new Server();
        appServer.setUrl(appUrl);
        appServer.setDescription("Api URL");

        Contact contact = new Contact();
        contact.setEmail("agoralabs.contact@gmail.com");
        contact.setName("AgoraLabs");
        contact.setUrl("https://www.agoralabs.org");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
            .title("Kaic Demo Spring Boot API")
            .version("1.0")
            .contact(contact)
            .description("This API exposes endpoints to manage Kaiac Demo Spring Boot Backend.").termsOfService("https://www.agoralabs.org/terms")
            .license(mitLicense);

            return new OpenAPI().info(info).servers(List.of(appServer));

    }
}