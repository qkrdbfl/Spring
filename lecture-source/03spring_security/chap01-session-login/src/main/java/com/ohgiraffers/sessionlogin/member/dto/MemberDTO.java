package com.ohgiraffers.sessionlogin.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class MemberDTO {

    private int no; //회원번호
    private String id; //회원아이디
    private String pwd; //회원비밀번호
    private String tempPwdYn; //임시비밀번호여부
    private Date pwdChangedDatetime; //회원비밀번호변경일자
    private String pwdExpDate; //회원비밀번호만료일자
    private String name; //회원이름
    private Date registDatetime; //회원가입일시

    /* TBL_MEMBER_ROLE - 한 멤버는 여러 권한을 가질 수 있다 */
    private List<MemberRoleDTO> memberRoleList; //권한 목록 참조
}
