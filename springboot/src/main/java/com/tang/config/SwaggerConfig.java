package com.tang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author 临渊
 * @Date 2022-09-13 7:42
 */

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    /**
     *  创建API应用
     *  apiInfo() 增加API相关信息
     *  通过select()函数返回一个ApiSelectorBuilder实例, 用来控制哪些接口暴露给Swagger来展现
     *  本例采用指定扫描的包路径来定义指定要建立的API的目录
     */
    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("标准接口")
                .apiInfo(apiInfo("Spring Boot中使用Swagger2构建RestFul APIs", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tang.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     *  创建API的基本信息(这些基本信息会展现在文档的页面中)
     *  访问地址: http://ip:prot/swagger-ui.html
     */
    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description("swagger生成API")
                .termsOfServiceUrl("termsOfServiceUrl")
                .contact(new Contact("name", "url", "email"))
                .version(version)
                .build();
    }

}
