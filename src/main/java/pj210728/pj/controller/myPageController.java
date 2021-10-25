package pj210728.pj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pj210728.pj.domain.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String updatePassword(PasswordForm form, ModelAndView mav, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = new Member();
        member.setName((String)session.getAttribute("userName"));
        member.setEmail((String)session.getAttribute("userMail"));
        member.setPassword(form.getCurPwd());

        return "";
    }
}
