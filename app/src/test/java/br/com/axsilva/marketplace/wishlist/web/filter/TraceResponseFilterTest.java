package br.com.axsilva.marketplace.wishlist.web.filter;

import io.micrometer.tracing.CurrentTraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class TraceResponseFilterTest {

    @Mock
    private Tracer tracer;

    @Mock
    private CurrentTraceContext currentTraceContext;

    @Mock
    private ServletRequest servletRequest;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpServletResponse httpServletResponse;

    private TraceResponseFilter traceResponseFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        traceResponseFilter = new TraceResponseFilter(tracer);
    }

    @Test
    void doFilter_when_correlationIdHeader_isnot_setted() throws ServletException, IOException {
        when(tracer.currentTraceContext()).thenReturn(currentTraceContext);
        when(currentTraceContext.context()).thenReturn(null);

        traceResponseFilter.doFilter(servletRequest, httpServletResponse, filterChain);

        verify(httpServletResponse, never()).setHeader(any(), any());
        verify(filterChain).doFilter(servletRequest, httpServletResponse);
    }

    @Test
    void doFilter_when_correlationIdHeader_is_setted() throws ServletException, IOException {
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        servletResponse.setHeader("correlation-id", "uuid");

        traceResponseFilter.doFilter(servletRequest, servletResponse, filterChain);

        verify(tracer, never()).currentTraceContext();
    }
}
