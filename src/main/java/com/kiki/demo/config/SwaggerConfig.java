package com.kiki.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private ApiInfo metaInfo(){
        Contact contact = new Contact("Kiki", "www.todo.kiki", "kiki@gmail.com");
        ApiInfo apiInfo = new ApiInfo("To do app", "This is a to do app", "1.0", "Terms of service",  contact,
                "licensed by the United States", "https://www.us.todo", new ArrayList<>());
        return apiInfo;
    }

    @Bean
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kiki.demo"))
                .paths(regex("/api/v1.*"))
                .build().apiInfo(metaInfo());

    }
}
