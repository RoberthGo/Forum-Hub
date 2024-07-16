package com.projects.personal.forum_hub.understructure.security;

import com.projects.personal.forum_hub.repository.UserRepository;
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
        String path = request.getRequestURI();
        if ("/register".equals(path) || "/login".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token =  authHeader.replace("Bearer ", "");
            String subject = tokenService.getSubject(token);
            if (subject != null) {
                var user = userRepository.findByEmail(subject);
                if (!user.isPresent())
                   throw new IllegalStateException("User does not exist");
                var authentication = new UsernamePasswordAuthenticationToken(user.get(), null,
                        user.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}