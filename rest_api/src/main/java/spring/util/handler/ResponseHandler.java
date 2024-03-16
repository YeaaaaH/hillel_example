package spring.util.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResponseHandler {
    public static void buildResponse(HttpServletResponse response, Object body) throws IOException {
        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }
}
