package pj210728.pj.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pj210728.pj.Mapper.MemberMapper;
import pj210728.pj.domain.Member;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MybatisMemberService {
    private final MemberMapper mapper;

    @Autowired
    public MybatisMemberService(MemberMapper mapper) {
        this.mapper = mapper;
    }

    private void validateDuplicateMember(Member member) {
        mapper.findByName(member.getName())
                // 만약 값이 있으면 람다식 실행
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원");
                });
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); // 중복 회원 검증
        mapper.save(member);

        return member.getId();
    }

    /**
     * 가입 한 회원인지 확인
     */
    public boolean LoginCheck(String name, String password) {
        Optional<Member> member = mapper.findByNamePassword(name, password);

        return member.isPresent();
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return mapper.findAll();
    }

    /**
     * 특정 회원 조회
     */
    public Optional<Member> findOneName(String memberName) {
        return mapper.findByName(memberName);
    }

    public Optional<Member> findOneId(Long memberId) {
        return mapper.findById(memberId);
    }

}
