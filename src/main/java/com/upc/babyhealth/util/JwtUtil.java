package com.upc.babyhealth.util;

import com.upc.babyhealth.models.entity.*;
import com.upc.babyhealth.models.service.GestanteService;
import com.upc.babyhealth.models.service.ObstetraService;
import com.upc.babyhealth.models.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Autowired
    private ObstetraService obstetraService;
    @Autowired
    private GestanteService gestanteService;
    @Autowired
    private UsuarioService usuarioService;

    private String secret = "babyhealth";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();

        SimpleGrantedAuthority role = (SimpleGrantedAuthority) authentication.getAuthorities().toArray()[0];


        if(role.getAuthority().toString().equals("ROLE_"+RolEnum.OBSTETRA.toString()) ){
            Obstetra o = obstetraService.findByUsername(authentication.getName());
            claims.put("EntityID",o.getId());
        }
        else if(role.getAuthority().toString().equals("ROLE_"+RolEnum.GESTANTE.toString())){
            Gestante g = gestanteService.findByUsername(authentication.getName());
            claims.put("EntityID",g.getId());
        }
        else{
            claims.put("EntityID", null);
        }

        claims.put("AUTHORITIES_KEY", authentication.getAuthorities());
        Usuario u = usuarioService.findByUsername(authentication.getName());
        claims.put("AceptoTerminos", u.getAceptoTerminos().toString());
        //claims.put("User", usuarioService.findByUsername(authentication.getName()));

        return createToken(claims, authentication);
    }

    private String createToken(Map<String, Object> claims, Authentication authentication) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
