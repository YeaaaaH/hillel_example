package spring.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExampleInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(ExampleInterceptor.class);
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("Intercepted preHandle in: " +  request.getRequestURI());
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) throws Exception {
        logger.info("Intercepted postHandle: " +  response.getStatus());
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                         @Nullable Exception ex) throws Exception {
        logger.info("Intercepted afterCompletion");
    }
}
