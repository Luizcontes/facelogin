// package D.demo.config;

// import java.util.ArrayList;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// // import io.swagger.models.Contact;
// // import io.swagger.annotations.Contact;
// import springfox.documentation.service.Contact;
// import static springfox.documentation.builders.PathSelectors.regex;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @Configuration
// @EnableSwagger2
// public class SwaggerConfig {
    
//     @Bean
//     public Docket productApi() {
//         return new Docket(DocumentationType.SWAGGER_2)
//             .select()
//             .apis(RequestHandlerSelectors.basePackage("D.demo"))
//             .paths(regex("/api.*"))
//             .build()
//             .apiInfo(metaInfo());
//     }

//     private ApiInfo metaInfo() {
//         ApiInfo apiInfo = new ApiInfo(
//             "Produtos API REST",
//             "API REST de cadastro de produtos.",
//             "1.0",
//             "Terms of Service",
//             new Contact("Luiz Contes", "https://luizcontes.github.io/",
//                 "luiz.contes@hotmail.com"),
//             "Apache License Version 2.0",
//             "https://www.apache.org/license.html", new ArrayList<>()
//         );
//         return apiInfo;
//     }
// }
