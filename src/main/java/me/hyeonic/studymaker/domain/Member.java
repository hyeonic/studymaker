package me.hyeonic.studymaker.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;

    private Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //== 정적 팩토리 메소드 ==//
    public static Member createMember(String email, String password) {
        Member member = new Member(email, password);
        return member;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
