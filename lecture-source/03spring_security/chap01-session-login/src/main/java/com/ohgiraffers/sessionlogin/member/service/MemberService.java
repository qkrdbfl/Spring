package com.ohgiraffers.sessionlogin.member.service;

import com.ohgiraffers.sessionlogin.member.dao.MemberMapper;
import com.ohgiraffers.sessionlogin.member.dto.MemberDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder){ //매퍼 추가
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder; //PasswordEncoder를 필드로 쓰기위해 의존성 주입
    }

    @Transactional //인셉션이면 롤백
    public void joinMember(MemberDTO member) {
        //평문으로 된 비밀번호를 암호화한다
        member.setPwd(passwordEncoder.encode(member.getPwd()));//비번 꺼내서 인코딩으로 사용하겠다
        //tbl_member 테이블에 정보 저장
        memberMapper.registMember(member);
        //tbl_member_role 테이블에 권한 정보 저장
        memberMapper.addMemberAuthority();
    }


}
