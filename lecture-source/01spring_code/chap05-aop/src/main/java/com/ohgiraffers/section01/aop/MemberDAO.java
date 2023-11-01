package com.ohgiraffers.section01.aop;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDAO {

    private final Map<Long, MemberDTO> memberMep;

    public MemberDAO(){
        memberMep = new HashMap<>();
        memberMep.put(1L, new MemberDTO(1L,"홍길동"));
        memberMep.put(2L, new MemberDTO(2L,"유관순"));
    }

    public Map<Long, MemberDTO> selectMembers() {
        return memberMep;
    }

    public  MemberDTO selectMember(Long id){

        MemberDTO returnMember = memberMep.get(id);

        //예외로 반환이 1도 2도 아니고 null일 때엔 인셉션이 발생하도록 썼음.(RuntimeException)
        if (returnMember == null) throw new RuntimeException("해당하는 id의 회원이 없습니다.");

        return returnMember;
    }
}
