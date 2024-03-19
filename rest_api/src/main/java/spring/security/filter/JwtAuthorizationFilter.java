package spring.security.filter;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import spring.exception.JwtTokenException;
import spring.security.jwt.TokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static spring.util.handler.ResponseHandler.buildResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    public JwtAuthorizationFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/auth/signin") ||
                request.getRequestURI().equals("/auth/signup") ||
                request.getRequestURI().contains("swagger") ||
                request.getRequestURI().contains("v3")) {
            filterChain.doFilter(request, response);
        } else {
            try {
                String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                DecodedJWT decodedJWT = tokenProvider.resolveToken(authHeader);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        decodedJWT.getSubject(),
                        null,
                        tokenProvider.getAuthoritiesFromToken(decodedJWT));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } catch (SignatureVerificationException exception) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                buildResponse(response, new JwtTokenException("TOKEN_DECLARATION_IS_WRONG"));
            } catch (TokenExpiredException expiredException) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                buildResponse(response, new TokenExpiredException("TOKEN_IS_EXPIRED"));
            } catch (Exception exception) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                buildResponse(response, "You are not authenticated");
            }
        }
    }
}
