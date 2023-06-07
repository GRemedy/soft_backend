package com.bistu.filter;


import com.alibaba.fastjson.JSONObject;
import com.bistu.entity.Result;
import com.bistu.utils.JWTUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;


@WebFilter(urlPatterns = "/*")
public class DefaultFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();    //获取请求的url

        //如果请求包含login或者signup，则直接放行
        if(url.contains("login")||url.contains("signup")||url.contains("code")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //获取token
        String jwt = req.getHeader("token");

        //判断token是否为空
        if(!StringUtils.hasLength(jwt)){
            Result error = new Result("0","NOT_LOGIN",null);
            String result = JSONObject.toJSONString(error);
            resp.getWriter().write(result);
            return;
        }

        //解析token
        try {
            JWTUtils.JWTParse(jwt);
        } catch (Exception e) {
            Result error = new Result("0","NOT_LOGIN",null);
            String result = JSONObject.toJSONString(error);
            resp.getWriter().write(result);
            return;
        }

        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }


}

