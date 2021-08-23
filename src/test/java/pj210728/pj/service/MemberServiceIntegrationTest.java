package pj210728.pj.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pj210728.pj.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 통합 테스트
// 컨테이너까지 불러와 함께 테스트 ( 오래 걸림 )
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MybatisMemberService memberService;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("test");
        member.setEmail("test@test.com");
        member.setPassword("1234");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOneId(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        member1.setEmail("test@test.com");
        Member member2 = new Member();
        member2.setName("spring");
        member2.setEmail("test@test.com");

        //when
        memberService.join(member1);
        // join(member2) 하면 exception 이 터져야 함 그러면 테스트 성공
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");

        //then
    }

}
