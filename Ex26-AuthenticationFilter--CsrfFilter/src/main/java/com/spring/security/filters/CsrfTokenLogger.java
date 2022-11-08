package com.spring.security.filters;

import org.jboss.logging.Logger;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import java.io.IOException;

public class CsrfTokenLogger implements Filter {

    private static final Logger logger = Logger.getLogger(CsrfTokenLogger.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        logger.info("CSRF token " + token.getToken());

        filterChain.doFilter(request, response);
    }
}
