package it.jacopo.keycloak.demo_backend.logging.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;

@Component
public class ControllerLoggingInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger("CONTROLLER");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTimeMs", System.currentTimeMillis());

        log.info("API REQUEST ➡️  {} {}", request.getMethod(), request.getRequestURI());
        log.debug("➡️  query={}", request.getQueryString());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {

        long start = (long) request.getAttribute("startTimeMs");
        long elapsed = System.currentTimeMillis() - start;

        if (request instanceof ContentCachingRequestWrapper req) {
            String body = new String(
                    req.getContentAsByteArray(),
                    StandardCharsets.UTF_8
            );
            log.info("API REQUEST ➡️  REQUEST BODY {}", body);
        }

        log.info("API RESPONSE ⬅️  {} {} -> status={} timeMs={}",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                elapsed
        );
        // RESPONSE BODY
        if (response instanceof ContentCachingResponseWrapper res) {
            String body = new String(
                    res.getContentAsByteArray(),
                    StandardCharsets.UTF_8
            );
            log.info("API RESPONSE ⬅️  RESPONSE BODY {}", body);
        }

        if (ex != null) {
            log.error("⬅️  exception on {} {}",
                    request.getMethod(), request.getRequestURI(), ex);
        }
    }
}