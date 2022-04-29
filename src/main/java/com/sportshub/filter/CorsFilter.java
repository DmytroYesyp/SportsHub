//package com.sportshub.filter;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CorsFilter implements Filter {
//
//    @Override
//    public void doFilter(final ServletRequest request, final ServletResponse response,
//                         final FilterChain chain) throws IOException, ServletException {
//
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
//        httpResponse.setHeader("Access-Control-Allow-Headers", "*");
//        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//        httpResponse.setHeader("Access-Control-Max-Age", "1209600");
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) { }
//
//    @Override
//    public void destroy() { }
//
//}