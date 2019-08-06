package com.pp.microserviceuserservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // 定义API接口映射路径
    public static final String DEFAULT_INCLUDE_PATTERN = "/user/.*";
    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);
    @Bean
    public Docket swaggerSpringfoxDocket() {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        ApiInfo apiInfo = new ApiInfo("用于管理API接口测试文档","description", "1.1.0",
                "termsOfServiceUrl", "contact", "", "");
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .genericModelSubstitutes(ResponseEntity.class)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .directModelSubstitute(java.time.LocalDate.class, String.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, String.class)
                .directModelSubstitute(java.time.LocalDateTime.class, String.class)
                .select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }
}
