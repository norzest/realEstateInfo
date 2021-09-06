package pj210728.pj.Mapper;

import org.apache.ibatis.annotations.*;
import pj210728.pj.domain.Member;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Insert("insert into member(name, eMail, password)" +
            "values(#{member.name}, #{member.eMail}, #{member.password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(@Param("member") Member member);

    @Select("select * from member where id = #{id}")
    Optional<Member> findById(@Param("id") Long id);

    @Select("select * from member where eMail = #{eMail}")
    Optional<Member> findByEmail(@Param("eMail") String eMail);

    @Select("select * from member where name = #{name}")
    Optional<Member> findByName(@Param("name") String name);

    @Select("select * from member where eMail = #{eMail} and password = #{password}")
    Optional<Member> findByEmailPassword(@Param("eMail") String eMail,
                                        @Param("password") String password);

    @Select("select * from member")
    List<Member> findAll();

}
