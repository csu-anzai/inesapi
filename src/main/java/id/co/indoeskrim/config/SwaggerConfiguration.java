/**
 * SwaggerConfiguration.java
 * @author paulusyansen
 * @date Apr 24, 2019
 */
package id.co.indoeskrim.config;

import static io.github.jhipster.config.JHipsterConstants.SPRING_PROFILE_SWAGGER;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;

import com.google.common.collect.Lists;

import io.github.jhipster.config.JHipsterProperties;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@AutoConfigureAfter
@Configuration
@ConditionalOnClass({ ApiInfo.class, BeanValidatorPluginsConfiguration.class })
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@Profile(SPRING_PROFILE_SWAGGER)
public class SwaggerConfiguration {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    
    public static final String STARTING_MESSAGE = "Starting Swagger";
    public static final String STARTED_MESSAGE = "Started Swagger in {} ms";
    public static final String MANAGEMENT_TITLE_SUFFIX = "management API";
    public static final String MANAGEMENT_GROUP_NAME = "management";
    public static final String MANAGEMENT_DESCRIPTION = "Management endpoints documentation";


    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    private final JHipsterProperties applicationProperties;

    public SwaggerConfiguration(JHipsterProperties applicationProperties){
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public Docket swaggerSpringfoxApiDocket() {
        String[] protocols = {};
        log.debug(STARTING_MESSAGE);
        StopWatch watch = new StopWatch();
        watch.start();
        Contact contact = new Contact(
            applicationProperties.getSwagger().getContactName(),
            applicationProperties.getSwagger().getContactUrl(),
            applicationProperties.getSwagger().getContactEmail());

        ApiInfo apiInfo = new ApiInfo(
                applicationProperties.getSwagger().getTitle(),
                applicationProperties.getSwagger().getDescription(),
                applicationProperties.getSwagger().getVersion(),
                applicationProperties.getSwagger().getTermsOfServiceUrl(),             
            contact,
            applicationProperties.getSwagger().getLicense(),
            applicationProperties.getSwagger().getLicenseUrl(),
            new ArrayList<>());

        Docket docket = createDocket()
            .host(applicationProperties.getSwagger().getHost())
            .protocols(new HashSet<>(Arrays.asList(protocols)))
            .apiInfo(apiInfo)
            .forCodeGeneration(true)
            .directModelSubstitute(ByteBuffer.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .select()
            //.paths(regex(applicationProperties.getSwagger().getDefaultIncludePattern()))
            .paths(PathSelectors.any()) 
            .apis(RequestHandlerSelectors.basePackage("id.co.indoeskrim.web.rest"))
            .build()
            .securityContexts(Lists.newArrayList(securityContext()))
//            .securitySchemes(Lists.newArrayList(securityScheme()));
            .securitySchemes(Lists.newArrayList(apiKey()));
        watch.stop();
        log.debug(STARTED_MESSAGE, watch.getTotalTimeMillis());
        return docket;
    }


    protected Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2);
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            //.forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
            .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
            new SecurityReference("JWT", authorizationScopes));
    }
}
