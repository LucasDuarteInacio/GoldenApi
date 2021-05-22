package com.goldenburguer.app.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  public static final String TITLE = "Project Golden Burguer";
  public static final String DESCRIPTION = "";
  public static final String VERSION = "1.0";
  public static final String LICENSE = "Apache 2.0";
  public static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";

  public static final String CUSTOMER = "Customer";
  public static final String PRODUCT = "Product";
  public static final String CATEGORY = "Category";
  public static final String OPTION = "Option";
  public static final String ORDER = "Order";
  public static final String ADDRESS = "Address";
  public static final String CHECKOUT = "Checkout";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .paths(Predicates.not(PathSelectors.regex("/error.*")))
        .build()
        .apiInfo(this.metaInfo())
        .tags(new Tag(CUSTOMER, "Operations related to the manipulation of the Customer entity."))
        .tags(new Tag(PRODUCT, "Operations related to the manipulation of the Product entity."))
        .tags(new Tag(CATEGORY, "Operations related to the manipulation of the Category entity."))
        .tags(new Tag(ORDER, "Operations related to the manipulation of the Order entity."))
        .tags(new Tag(ADDRESS, "Operations related to the manipulation of the Address entity."))
        .tags(new Tag(CHECKOUT, "Operations related to the manipulation of the Checkout entity."))
        .tags(new Tag(OPTION, "Operations related to the manipulation of the option entity."));
  }

  @SuppressWarnings("rawtypes")
  private ApiInfo metaInfo() {
    return new ApiInfoBuilder()
        .title(TITLE)
        .description(DESCRIPTION)
        .version(VERSION)
        .license(LICENSE)
        .licenseUrl(LICENSE_URL)
        .build();
  }
}
