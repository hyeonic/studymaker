package me.hyeonic.studymaker.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    @DisplayName("정적 팩토리 메소드를 확인하는 테스트")
    void saveEntity() {

        // given
        String email = "a@aaa.com";
        String password = "123";

        // when
        Member savedMember = Member.createMember(email, password);

        // then
        Assertions.assertThat(savedMember.getEmail()).isEqualTo(email); // 이메일 확인
        Assertions.assertThat(savedMember.getPassword()).isEqualTo(password); // 비밀번호 확인
    }

    @Test
    @DisplayName("비밀번호를 변경하는 메소드를 확인하는 테스트")
    void changedPassword() {

        // given
        String email = "a@aaa.com";
        String password = "123";
        Member savedMember = Member.createMember(email, password);

        // when
        savedMember.changePassword("1234");

        // then
        Assertions.assertThat(savedMember.getPassword()).isEqualTo("1234");
    }
}