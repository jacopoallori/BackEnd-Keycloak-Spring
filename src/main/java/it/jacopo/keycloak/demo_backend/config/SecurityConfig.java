package it.jacopo.keycloak.demo_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                // backend stateless
                .csrf(csrf -> csrf.disable())

                // tutte le richieste devono essere autenticate
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/capo/**").hasRole("CAPO")
                        .requestMatchers("/api/senior/**").hasRole("SENIOR")
                        .requestMatchers("/api/junior/**").hasRole("JUNIOR")
                        .anyRequest().authenticated()
                )

                // abilita OAuth2 Resource Server (JWT)
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );

        return http.build();
    }


    /**
     * Converter dei ruoli dal JWT di Keycloak
     */
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            // Nome del client in Keycloak
            String clientId = "Keycloak_Prova";

            // Prende i ruoli del client dal token
            Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims()
                    .getOrDefault("resource_access", Map.of());

            Map<String, Object> clientRolesMap = (Map<String, Object>) resourceAccess
                    .getOrDefault(clientId, Map.of());

            List<String> roles = (List<String>) clientRolesMap.getOrDefault("roles", List.of());

            // Trasforma i ruoli in GrantedAuthority
            return roles.stream()
                    .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                    .collect(Collectors.toList());
        });

        return converter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // frontend
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
