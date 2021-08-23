package pj210728.pj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pj210728.pj.domain.Member;
import pj210728.pj.service.MybatisMemberService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MemberController {

    private final MybatisMemberService memberService;

    // 컴포넌트 스캔을 통한 자동 의존관계 주입
    // 컨테이너에서 MemberService를 가져와
    // 한개만(Singleton) 사용할 수 있도록 관리
    @Autowired
    public MemberController(MybatisMemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public ModelAndView create(MemberForm form, ModelAndView mav) {
        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        try {
            memberService.join(member);
            mav.addObject("data", new MessageForm("회원가입 성공", "/"));
            mav.setViewName("message");
            return mav;
        } catch (IllegalStateException e) {
            mav.addObject("data", new MessageForm("이미 존재하는 이름입니다", "/members/new"));
            mav.setViewName("message");
            return mav;
        }
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }

    @GetMapping("/members/login")
    public String loginForm() {
        return "members/memberLogin";
    }

    @PostMapping("/members/login")
    public ModelAndView login(MemberForm form, ModelAndView mav) {
        boolean check = memberService.LoginCheck(form.getName(), form.getPassword());

        if (check) {
            mav.addObject("data", new MessageForm("로그인 성공", "/"));
        } else {
            mav.addObject("data", new MessageForm("로그인 실패", "/members/login"));
        }

        mav.setViewName("message");
        return mav;
    } // login

}

