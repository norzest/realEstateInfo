package pj210728.pj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class TestController {
    @GetMapping("/sessionTest")
    public String sessionTest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String mail = (String) session.getAttribute("userName");

        return mail != null ?
                mail : "session not created";
    }
}
