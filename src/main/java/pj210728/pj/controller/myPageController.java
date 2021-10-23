package pj210728.pj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pj210728.pj.domain.Member;

@Controller
public class myPageController {
    @GetMapping("/members/myPage")
    public String getMyPage() {
        return "members/afterLogin/myPage";
    }

    // 비밀번호 변경
    @GetMapping("/members/myPage/updatePassword")
    public String updatePasswordForm() {
        return "members/afterLogin/updatePasswordForm";
    }

    @PostMapping("/members/myPage/updatePassword")
    public String updatePassword() {


        return "";
    }
}
