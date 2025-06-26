package com.cefet.ds_projeto.config;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.http.HttpMethod; 
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; 
import org.springframework.security.config.annotation.authentication.configuration.*; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.security.web.SecurityFilterChain; 
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; 
import com.cefet.ds_guia11.security.JwtAuthenticationFilter; 
import com.cefet.ds_guia11.services.UsuarioDetailsService;

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
                .ignoringRequestMatchers("/h2-console/**", "/usuarios/**", "/auth/login") // Ignorar CSRF para esses endpoints 
            ) 
            .authorizeHttpRequests(auth -> auth

    
    .requestMatchers("/h2-console/**").permitAll()
    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

   
    .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() // Cadastro de novo usuário
    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Login

  
    .requestMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN")
    .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")

   
    .requestMatchers(HttpMethod.GET, "/despesas").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.GET, "/despesas/{id}").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.POST, "/despesas").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.PUT, "/despesas/**").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.DELETE, "/despesas/**").hasAnyRole("USER", "ADMIN")

    
    .requestMatchers(HttpMethod.GET, "/receitas").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.GET, "/receitas/{id}").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.POST, "/receitas").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.PUT, "/receitas/**").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.DELETE, "/receitas/**").hasAnyRole("USER", "ADMIN")

    
    .requestMatchers(HttpMethod.GET, "/categorias").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.GET, "/categorias/{id}").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.POST, "/categorias").hasRole("ADMIN") // Apenas admin pode criar categorias
    .requestMatchers(HttpMethod.PUT, "/categorias/**").hasRole("ADMIN")
    .requestMatchers(HttpMethod.DELETE, "/categorias/**").hasRole("ADMIN")

    .anyRequest().authenticated()
)
 
}
}