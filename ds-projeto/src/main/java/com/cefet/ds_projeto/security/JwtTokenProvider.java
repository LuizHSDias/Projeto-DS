package com.cefet.ds_projeto.security;

@Component 
public class JwtTokenProvider { 
 
    private final long jwtExpirationInMs; 
    private final Key key; 
 
    public JwtTokenProvider(@Value("${jwt.secret}") String jwtSecret, 
                            @Value("${jwt.expiration}") long jwtExpirationInMs) { 
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)); 
        this.jwtExpirationInMs = jwtExpirationInMs; 
    } 
 
    public String generateToken(Authentication authentication) { 
        UsuarioDetails userPrincipal = (UsuarioDetails) authentication.getPrincipal(); 
        String roles = userPrincipal.getAuthorities().stream() 
                .map(GrantedAuthority::getAuthority) 
                .collect(Collectors.joining(",")); 
 
        Date now = new Date(); 
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs); 
 
        return Jwts.builder() 
                .setSubject(userPrincipal.getUsername()) 
                .claim("roles", roles) 
                .setIssuedAt(now)  
                .setExpiration(expiryDate) 
                .signWith(this.key, SignatureAlgorithm.HS512)  
                .compact(); 
    } 
 
    public String getUsernameFromJWT(String token) { 
        Claims claims = Jwts.parserBuilder() 
                .setSigningKey(this.key)  
                .build() 
                .parseClaimsJws(token) 
                .getBody(); 
        return claims.getSubject(); 
    } 
 
    public boolean validateToken(String authToken) { 
        try { 
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(authToken);  
            return true; 
        } catch (Exception ex) { 
            System.out.println("A validação do JWT falhou: " + ex.getMessage()); 
        } 
        return false; 
    }     
}