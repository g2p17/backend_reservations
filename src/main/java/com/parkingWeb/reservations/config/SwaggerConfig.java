package com.parkingWeb.reservations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.parkingWeb.reservations.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                ;
    }
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "ParkingWEB API",
                "Java-based microservice for booking management of the ParkingWeb API",
                "1.0",
                "http://parkingweb.com/terms",
                new Contact("ParkingWEB", "https://parkingweb.com", "info@parkingweb.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}