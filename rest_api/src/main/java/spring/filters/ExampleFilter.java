package spring.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*" })
public class ExampleFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(ExampleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        long time = System.currentTimeMillis();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            time = System.currentTimeMillis() - time;
            logger.info("Request was processed in:" +  ((HttpServletRequest) servletRequest).getRequestURI() + " "+ time + " ms ");
//            String.format()
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
