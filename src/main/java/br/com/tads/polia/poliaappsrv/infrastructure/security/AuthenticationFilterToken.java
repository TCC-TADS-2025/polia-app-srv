package br.com.tads.polia.poliaappsrv.infrastructure.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilterToken extends OncePerRequestFilter {

    private JwtTokenProvider tokenService;

    public AuthenticationFilterToken(JwtTokenProvider tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recoverToken(request);

        boolean isValid = tokenService.isTokenValid(token);
        if(isValid)
            authUser(token);

        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }


    private void authUser(String token) {
        UserDTO user = tokenService.decodeUserFromToken(token);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(
            new SimpleGrantedAuthority(user.getRole().getAuthority())
        );

        authorities.addAll(
            user.getScopes().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList())
        );

        var authentication = new PreAuthenticatedAuthenticationToken(user, token, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}