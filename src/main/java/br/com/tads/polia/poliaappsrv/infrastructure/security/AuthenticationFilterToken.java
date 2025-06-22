package br.com.tads.polia.poliaappsrv.infrastructure.security;

import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilterToken extends OncePerRequestFilter {

    private JwtTokenProvider provider;

    public AuthenticationFilterToken(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }

}