package com.estudos.notificacao.exceptions;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CorrelationIdFilter implements Filter {
   @Generated
   private static final Logger log = LoggerFactory.getLogger(CorrelationIdFilter.class);
   private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
   private static final String TRACE_ID_HEADER = "X-Trace-ID";
   private static final String MDC_CORRELATION_ID = "correlationId";
   private static final String MDC_TRACE_ID = "traceId";

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest)request;
      HttpServletResponse httpResponse = (HttpServletResponse)response;
      String correlationId = httpRequest.getHeader("X-Correlation-ID");
      if (correlationId == null || correlationId.isEmpty()) {
         correlationId = UUID.randomUUID().toString();
      }

      String traceId = httpRequest.getHeader("X-Trace-ID");
      if (traceId == null || traceId.isEmpty()) {
         traceId = UUID.randomUUID().toString();
      }

      MDC.put("correlationId", correlationId);
      MDC.put("traceId", traceId);
      httpResponse.setHeader("X-Trace-ID", traceId);
      httpResponse.setHeader("X-Correlation-ID", correlationId);

      try {
         log.info("\ud83d\udd39 Incoming request - Method: {}, URI: {}", httpRequest.getMethod(), httpRequest.getRequestURI());
         chain.doFilter(request, response);
         log.info("\ud83d\udd39 Outgoing response - Status: {}", httpResponse.getStatus());
      } finally {
         MDC.clear();
      }

   }
}
