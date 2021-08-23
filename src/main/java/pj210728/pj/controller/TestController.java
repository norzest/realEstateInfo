package pj210728.pj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    @GetMapping("/sessionTest")
    public String sessionTest(HttpServletRequest request) {
        return request.getSession(true) != null ?
                "session created" : "session not created";
    }
}
