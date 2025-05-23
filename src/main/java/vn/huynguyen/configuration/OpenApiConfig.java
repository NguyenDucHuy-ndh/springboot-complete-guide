package vn.huynguyen.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("!prod")
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${open.api.title}") String title,
                           @Value("${open.api.version}") String version,
                           @Value("${open.api.description}") String description,
                           @Value("${open.api.serverUrl}") String serverUrl,
                           @Value("${open.api.serverName}") String serverName) {
        return new OpenAPI()
                .servers(List.of(new Server().url(serverUrl).description(serverName)))
                .info(new Info().title(title)
                        .version(version)
                        .description(description)
                        .license(new License().name("hkjh").url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                ;

    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/api/v1/**")
                .packagesToScan("vn.huynguyen.controller")
                .build();
    }
}
