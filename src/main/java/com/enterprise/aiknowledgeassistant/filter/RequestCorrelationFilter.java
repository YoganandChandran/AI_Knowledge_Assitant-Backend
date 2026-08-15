package com.enterprise.aiknowledgeassistant.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestCorrelationFilter
        extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain)
            throws IOException, ServletException {

        String correlationId =
                UUID.randomUUID().toString();

        MDC.put("correlationId",
                correlationId);

        response.setHeader(
                "X-Correlation-ID",
                correlationId);

        chain.doFilter(request,
                response);

        MDC.clear();

    }

}