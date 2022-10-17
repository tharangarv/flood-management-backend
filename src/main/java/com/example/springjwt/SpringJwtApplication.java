package com.example.springjwt;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@SpringBootApplication
public class SpringJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
        return FirebaseMessaging.getInstance(app);
    }


//    @Bean
//    CommandLineRunner runRole(RoleService roleService) {
//        return args -> {
//            roleService.saveRole(RoleDTO.builder()
//                    .userRole("Administrator")
//                    .build());
//
//            roleService.saveRole(RoleDTO.builder()
//                    .userRole("Moderator")
//                    .build());
//        };
//    }
//
//    @Bean
//    CommandLineRunner runUser(UserService userService) {
//        return args -> {
//
//            userService.saveUser(UserDTO.builder()
//                    .name("sachith")
//                    .email("sachith@gmail.com")
//                    .password("password123")
//                    .role("Admin")
//                    .build());
//
//            userService.saveUser(UserDTO.builder()
//                    .name("Savin")
//                    .email("savin@gmail.com")
//                    .password("password123")
//                    .role("Admin")
//                    .build());
//        };
//    }

}
