package lk.sliit.skillexplorer.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll()  // allow unauthenticated access to public endpoints
                        .anyRequest().authenticated()  // all others need auth
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt()  // enable JWT decoding
                );

        return http.build();
    }
}
