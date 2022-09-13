package com.obider.expensetrackerapi.middleware;

import com.obider.expensetrackerapi.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthMiddleware extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpRequest.getHeader("Authorization");
        if(authHeader==null){
            httpResponse.sendError(403,"Missing token");
            return;
        }
        String[] authHeaderArr = authHeader.split("Bearer ");
        if (authHeaderArr.length<=1 || authHeaderArr[1] == null){
            httpResponse.sendError(403,"Invalid/expired token 1");
            return;
        }

        String token = authHeaderArr[1];
//        try{
            Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                    .parseClaimsJws(token).getBody();
            Integer userId = Integer.parseInt(claims.get("userId").toString());
            httpRequest.setAttribute("userId",userId);

//        } catch (Exception e){
//           e.printStackTrace();
//            httpResponse.sendError(403,"Invalid/expired token 3");
//            return;
//        }

        filterChain.doFilter(servletRequest,servletResponse);


    }
}
