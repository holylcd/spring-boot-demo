package org.holy.spring.boot.quick.config.response.error;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author holy
 * @version 1.0.0
 * @date 2019/9/4 15:52
 */
@Controller
public class CustomErrorController extends BasicErrorController {

    public CustomErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        Map<String, Object> body = new LinkedHashMap<>(4);
        body.put("code", status.value());
        body.put("msg", status.getReasonPhrase());
        return new ResponseEntity<>(body, status);
    }


}
