package pj210728.pj.repository;

import pj210728.pj.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    // Optional -> Id나 Name을 가져올때 null일 경우 예외 처리 해줌줌
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

