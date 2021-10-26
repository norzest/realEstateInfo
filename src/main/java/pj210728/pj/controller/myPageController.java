package pj210728.pj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pj210728.pj.domain.Member;
import pj210728.pj.service.MybatisMemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class myPageController {

    private final MybatisMemberService memberService;

    @Autowired
    public myPageController(MybatisMemberService memberService) {
        this.memberService = memberService;
    }


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
    public ModelAndView updatePassword(PasswordForm form, ModelAndView mav, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = new Member();
        member.setEmail((String)session.getAttribute("userMail"));
        member.setPassword(form.getCurPwd());

        // 이메일이 존재하며 현재 비밀번호가 일치한다면
        boolean isPresentCheck = memberService.LoginCheck(member.getEmail(), member.getPassword());
        // 변경할 비밀번호 두가지가 일치한다면
        boolean newPwdCheck = form.getNewPwd1().equals(form.getNewPwd2());
        if (isPresentCheck & newPwdCheck) {
            memberService.updatePassword(member.getEmail(), form.getNewPwd1());
            mav.addObject("data", new MessageForm("비밀번호 변경 성공", "/members/myPage"));
        } else {
            mav.addObject("data", new MessageForm("비밀번호 변경 실패", "/members/myPage"));
        }

        mav.setViewName("message");
        return mav;
    }

    // 회원 탈퇴
    @GetMapping("/members/myPage/leaveAccount")
    public ModelAndView askLeaveAccount(ModelAndView mav, HttpServletRequest request) {
        HttpSession session = request.getSession();
        // DB 에서 해당 멤버 삭제
        memberService.deleteMember((String)session.getAttribute("userMail"));
        
        // 세션 초기화
        session.invalidate();
        request.getSession(true);
        mav.addObject("data", new MessageForm("회원 탈퇴 성공", "/"));
        mav.setViewName("message");
        return mav;
    }
}
