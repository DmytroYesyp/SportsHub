package com.sportshub.filter;

import com.sportshub.dto.user.data.UserData;
import com.sportshub.exception.UnauthenticatedException;
import com.sportshub.service.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.sportshub.constants.Constants.AUTH_TOKEN_HEADER;
import static com.sportshub.constants.Constants.TOKEN_PREFIX;
import static com.sportshub.constants.Constants.USER_DATA_CLAIMS;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;
    private final JwtService jwtService;
    private final String jwtSigningKey;

    public JwtTokenFilter(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse,
                          JwtService jwtService,
                          @Value("${JWT_SIGNING_KEY}") String jwtSigningKey) {

        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.jwtService = jwtService;
        this.jwtSigningKey = jwtSigningKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String authHeader = httpServletRequest.getHeader(AUTH_TOKEN_HEADER);
        if (authHeader == null) {
            chain.doFilter(request, response);
            return;
        }

        String[] authHeaderParts = authHeader.trim().split("\\s+");
        if (authHeaderParts.length != 2 || !TOKEN_PREFIX.equals(authHeaderParts[0])) {
            throw new UnauthenticatedException("Invalid format of Auth header!");
        }

        String jwtToken = authHeaderParts[1];

        Map<String, Object> userData;
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJws(jwtToken).getBody();
            userData = claims.get(USER_DATA_CLAIMS, Map.class);
        } catch (RuntimeException e) {
            throw new UnauthenticatedException("Invalid JWT token!");
        }

        Long userId = (long) (Integer) userData.get("id");
        List<String> roles = (List<String>) userData.get("roles");

        UserData user = new UserData(userId, List.of());
        String refreshedJwtToken = jwtService.generateToken(user);

        httpServletResponse.setHeader(AUTH_TOKEN_HEADER, refreshedJwtToken);

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                user, null,
                user == null ?
                        List.of() : roles.stream().map(role -> (GrantedAuthority) () -> role).toList()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}
