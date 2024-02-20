package com.example.beermarket.config;

import com.example.beermarket.model.RegionalDirector;
import com.example.beermarket.role.Role;
import com.example.beermarket.services.RegionalDirectorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig extends GlobalMethodSecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner initializeRegionalDirectors(final RegionalDirectorService regionalDirectorService,
                                                         final PasswordEncoder encoder) {
        return args -> {

            if (regionalDirectorService.findByLogin("admin") == null) {
                RegionalDirector adminDirector = new RegionalDirector();
                adminDirector.setLogin("admin");
                adminDirector.setPassword(encoder.encode("p"));
                adminDirector.setRole(Role.ADMIN);
                // Set other properties as needed
                regionalDirectorService.saveRegionalDirector(adminDirector);
            }


            // Add more regional directors if needed
        };
    }
}
