package com.cefet.ds_projeto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cefet.ds_projeto.security.JwtAuthenticationFilter;
import com.cefet.ds_projeto.services.UsuarioDetailsService;

@Configuration
public class SecurityConfig {

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**", "/usuarios/**", "/auth/login") // Ignora CSRF para esses endpoints
            )
            .authorizeHttpRequests(auth -> auth
                // Libera acesso ao H2 Console e Swagger
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                // Libera cadastro e login
                .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                // Usuários
                .requestMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")

                // Despesas
                .requestMatchers(HttpMethod.GET, "/despesas").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/despesas/{id}").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/despesas").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/despesas/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/despesas/**").hasAnyRole("USER", "ADMIN")

                // Receitas
                .requestMatchers(HttpMethod.GET,"/receitas").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/receitas/{id}").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/receitas").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/receitas/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/receitas/**").hasAnyRole("USER", "ADMIN")

                // Categorias
                .requestMatchers(HttpMethod.GET, "/categorias").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/categorias/{id}").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/categorias").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/categorias/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/categorias/**").hasRole("ADMIN")

                // Qualquer outra requisição precisa de autenticação
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions().disable()) // Necessário para H2 Console
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
