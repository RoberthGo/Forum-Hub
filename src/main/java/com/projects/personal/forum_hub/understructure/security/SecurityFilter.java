/*package com.projects.personal.forum_hub.understructure.security;

import com.projects.personal.forum_hub.repository.UserRepository;
import com.projects.personal.forum_hub.service.ServiceToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private ServiceToken tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            String email;
            try {
                email = tokenService.getSubject(token);
                if (email != null) {
                    var usuario = userRepository.selectUserByEmail(email);
                    //System.out.println(usuario.get().getEmail());
                    if (!usuario.isPresent())
                        throw new IllegalStateException("User does not exist");
                    var authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null,
                            usuario.get().getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (IllegalStateException e) {
                throw new IllegalStateException();
            }
        }
        filterChain.doFilter(request, response);
    }
}*/