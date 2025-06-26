package com.cefet.ds_projeto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cefet.ds_projeto.dto.JwtAuthenticationDTO;
import com.cefet.ds_projeto.dto.LoginDTO;
import com.cefet.ds_projeto.security.JwtTokenProvider;

public class JwtAuthenticationFilter extends OncePerRequestFilter { 
 
    @Autowired 
    private JwtTokenProvider tokenProvider; 
 
    @Autowired 
    private UsuarioDetailsService usuarioDetailsService; 
 
    @Override 
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException { 
        try { 
            String jwt = getJwtFromRequest(request); 
 
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) { 
                String username = tokenProvider.getUsernameFromJWT(jwt); 
                UserDetails userDetails = usuarioDetailsService.loadUserByUsername(username); 
                UsernamePasswordAuthenticationToken authentication = new  
                UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
                SecurityContextHolder.getContext().setAuthentication(authentication); 
            } 
        } catch (Exception ex) { 
            System.out.println("Não foi possível definir a autenticação do usuário no contexto de segurança: "  
            + ex.getMessage()); 
        } 
        filterChain.doFilter(request, response); 
    } 
 
    private String getJwtFromRequest(HttpServletRequest request) { 
        String bearerToken = request.getHeader("Authorization"); 
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) { 
            return bearerToken.substring(7); 
        } 
        return null; 
    } 
}