package com.sportshub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static String key;

    @Service
    @Component("propertiesConf")
    public static class PropertiesConf{

        private final String foo;

        @Autowired
        public PropertiesConf(@Value("${security.cfg.key}") String foo) {
            this.foo = foo;
            key = foo;
        }

    }

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JWTFilter customAuthFilter = new JWTFilter(authenticationManagerBean(),key);

        http.cors().and().authorizeRequests().antMatchers(POST, "/login", "/forgot_password","/reset_password").permitAll();

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(GET,"/", "/swagger-ui/**",
                "/swagger-resources/**", "/v2/api-docs").permitAll();
        http.authorizeRequests().antMatchers(POST,"/api/users/registerUser").permitAll();
        http.authorizeRequests().antMatchers(POST,"/login","/main", "/send_newsletter").permitAll();
        http.authorizeRequests().antMatchers(DELETE,"/follows/{id}").permitAll();
        http.authorizeRequests().antMatchers(GET,"/news", "/sport-kinds", "/news/{id}", "/leagues/{id}","/api/socialLogIn/getAllLogIn", "/api/socialShare/**", "/api/socialFollow/getAllFollows",
                "/language", "/comments", "/comments/news{id}", "/api/users/users{userId}", "/news/mostpop", "/teams/{id}",
                "/comments/mostcomm", "/datelimits/{id}","/datelimits", "/likes", "/likes/count", "/likes/check",
                "/dislikes", "/dislikes/count", "/dislikes/check", "/follows", "/api/fireBaseVideo/getVideos", "/api/fireBaseVideo/getVideos{params}", "/newsletter").permitAll();
        http.authorizeRequests().antMatchers(PUT,"/news/{id}", "/datelimits/{id}").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthFilter);
        http.addFilterBefore(new CustomAuthFilter(key), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "PATCH",
                "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type",
                "x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}
