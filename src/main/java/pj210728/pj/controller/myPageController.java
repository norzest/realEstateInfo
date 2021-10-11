package pj210728.pj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class myPageController {
    @GetMapping("/members/myPage")
    public String getMyPage() {
        return "members/afterLogin/myPage";
    }
}
