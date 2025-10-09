package study.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF (necessary for REST APIs)
            .csrf(csrf -> csrf.disable())

            // Allow CORS requests from frontend
            .cors(cors -> cors.disable()) // if you handle CORS separately

            // Define which endpoints are public
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // allow signup & login
                .requestMatchers("/api/quotes/**").permitAll()
                .requestMatchers("/api/growth/**").permitAll()
                .requestMatchers("/api/timer/**").permitAll()
                .requestMatchers("/api/tasks/**").permitAll() // or secure later
                .anyRequest().authenticated()
            )

            // Disable default form login
            .formLogin(form -> form.disable())

            // Disable basic auth popup
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
