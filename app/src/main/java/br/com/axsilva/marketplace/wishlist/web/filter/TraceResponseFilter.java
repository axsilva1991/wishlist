package br.com.axsilva.marketplace.wishlist.web.filter;

import io.micrometer.tracing.CurrentTraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.Filter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class TraceResponseFilter implements Filter {

    private static final String CORRELATION_ID_HEADER_NAME = "correlation-id";
    private final Tracer tracer;

    public TraceResponseFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        if (!response.getHeaderNames().contains(CORRELATION_ID_HEADER_NAME)) {
            if(Optional.of(tracer).map(Tracer::currentTraceContext).map(CurrentTraceContext::context).isEmpty()) {
                chain.doFilter(req, res);
                return;
            }
            var correlationId = tracer.currentTraceContext().context().traceId();
            response.setHeader(CORRELATION_ID_HEADER_NAME, correlationId);
        }
        chain.doFilter(req, res);
    }
}
