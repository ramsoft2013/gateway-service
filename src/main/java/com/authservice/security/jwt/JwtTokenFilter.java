package com.authservice.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
      //  System.out.println("doFilterInternal ::"+jwtTokenProvider.validateToken(token));
        if (!Objects.isNull(token) && jwtTokenProvider.validateToken(token)) {
        	 System.out.println("doFilterInternal 2::");
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            System.out.println("doFilterInternal 3 auth ::" + auth.getCredentials());
            System.out.println("doFilterInternal 3 auth details ::" + auth.getDetails());
            if (!Objects.isNull(auth))
                SecurityContextHolder.getContext().setAuthentication(auth);
        }
        System.out.println("doFilterInternal end ::");
       
     
       
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-CSRF-Token");
        response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
		// res.setHeader("Content-Type", "application/json;charset=utf-8"); // Opening this comment will cause problems
		
        filterChain.doFilter(request, response);
    }
}
