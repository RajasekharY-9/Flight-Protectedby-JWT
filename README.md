
JWT Implementation:

First Add spring security dependencies and Jwt as well

<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if you prefer -->
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
		
If you want to store the user details in database then you can use mysql and data jpa also

1.To Implement security we need a user table to be created in db to store user info so create User class with fields like username,password,roles

2.Now Create a Repository with findByUsername method

3.Create CustomUserDetailsService which should implement UserDetailsService and override loadByUsername method

4.Now Create POJO Classes for AuthRequest which contains username and passowrd

5.Create POJO AuthResponse which contain Token

6.Create JWTUtil which contains the major methods to create token , validate token,extract username etc methods

7.First our request will come to JwtFilter it will contain doFilterInternal method which will validate our token by taking help of util methods.

8.SecurityConfig Class is reponsible to route requests if here we provide any path which no need any authentication or authorization we can permit such paths
Then we can keep remaining all methods to be authenticated.

9.AuthController is reponsible for all here we will have register and login methods to create user and to genarate token


		


To Implemet Swagger in a JWT Based Rest API

Add dependency

<!-- To implemet Swagger use this-->
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.2.0</version>
		</dependency>
		
		
		
Ceate Swagger Config class to authorize tokens

package com.flight.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "My API", version = "v1"),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
    // This config enables JWT support in Swagger UI
}

In securtity filter chain add allow swagger paths

       .authorizeHttpRequests()
            .requestMatchers("/auth/**","/v3/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
				
Create a WEBconfig class to load all the end points
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }
}
				


		
