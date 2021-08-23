package pj210728.pj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pj210728.pj.Mapper.MemberMapper;
import pj210728.pj.aop.TimeTraceAop;
import pj210728.pj.repository.JdbcTemplateMemberRepository;
import pj210728.pj.repository.MemberRepository;
import pj210728.pj.service.MemberService;
import pj210728.pj.service.MybatisMemberService;

import javax.sql.DataSource;

// @Service 와 @Repository 와 같은 컴포넌트 스캔 외에도
// 자바 코드로 직접 스프링 빈(@Bean) 을 등록하여
// 별도로(SpringConfig) 관리할 수 있음
// 단, 컨트롤러는 @Autowired 로 같음
@Configuration
public class PjConfig {

    // Jdbc
    private final DataSource dataSource;
    @Autowired
    public PjConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    */

    /*
    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }
    */


}

