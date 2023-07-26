package cn.yy.reggie.filter;

import cn.yy.reggie.common.BaseContext;
import cn.yy.reggie.common.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();

        String[] allowedUris = {"/employee/login", "/employee/logout", "/backend/**", "/front/**"};

        if (!checkUri(allowedUris, requestURI)) {
            if (request.getSession().getAttribute("employee") == null){
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.getWriter().print(objectMapper.writeValueAsString(R.error("NOLOGIN")));
                return;
            }else {
                BaseContext.setCurrentId((Long) request.getSession().getAttribute("employee"));
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean checkUri(String[] allowedUris, String requestURI) {

        for (String uri : allowedUris) {
            if (antPathMatcher.match(uri, requestURI)) {
                return true;
            }
        }
        return false;
    }
}
