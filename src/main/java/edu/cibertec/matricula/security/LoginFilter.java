/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.cibertec.matricula.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.Collections;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author miguel
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter{
    
    public LoginFilter(String url, AuthenticationManager authManager){
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //Obtenemos el body de la peticion que debe venir en formato JSON.
        InputStream body = request.getInputStream();
        
        //Elo body tendra el formato JSON {"usuario":"ask", "clave":"123"}
        //Relizarmos el mapeo a nuestra clase Entity
        UsuarioEntity user = new ObjectMapper().readValue(body, UsuarioEntity.class);
        
        //Finalmente hacemos la autenticacion.
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsuario(), user.getClave(), Collections.emptyList()));
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth){
        //Si la autenticacion fue exitosa, agregamos el token a la respuesta.
        JwtUtil.addAuthentication(res, auth.getName());
    }
    
}
