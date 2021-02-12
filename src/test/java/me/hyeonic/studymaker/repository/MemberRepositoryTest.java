package me.hyeonic.studymaker.repository;

import me.hyeonic.studymaker.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @DisplayName("member 를 1명 추가하는 테스트")
    void saveMember() {

        // given
        String email = "a@aaa.com";
        String password = "123";

        Member member = Member.createMember(email, password);
        Member savedMember = memberRepository.save(member);

        // when
        Member findMember = memberRepository.findById(savedMember.getId()).get();

        // then
        assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    @DisplayName("findByEmailAndPassword 조회하는 테스트")
    void findByEmail() {

        // given
        String email = "a@aaa.com";
        String password = "123";
        Member member = Member.createMember(email, password);
        Member savedMember = memberRepository.save(member);

        // when
        Member findMember = memberRepository.findByEmail(email).get();

        // then
        assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    @DisplayName("findByEmailAndPassword 조회에 실패하는 테스트")
    void findByEmail_fail() {

        // given
        String email = "a@aaa.com";
        String password = "123";
        Member member = Member.createMember(email, password);

        // when
        memberRepository.save(member);

        // then
        assertThrows(NoSuchElementException.class, () -> {
            Optional<Member> optionalMember = memberRepository.findByEmail("b@bbb.com");
            optionalMember.get();
        });
    }

    @Test
    @DisplayName("findByEmailAndPassword 조회하는 테스트")
    void findByEmailAndPassword() {

        // given
        String email = "a@aaa.com";
        String password = "123";
        Member member = Member.createMember(email, password);
        Member savedMember = memberRepository.save(member);

        // when
        Member findMember = memberRepository.findByEmailAndPassword(email, password).get();

        // then
        assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    @DisplayName("findByEmailAndPassword 조회에 실패하는 테스트")
    void findByEmailAndPassword_fail() {

        // given
        String email = "a@aaa.com";
        String password = "123";
        Member member = Member.createMember(email, password);

        // when
        memberRepository.save(member);
        memberRepository.findByEmailAndPassword(email, "1234");

        // then
        assertThrows(NoSuchElementException.class, () -> {
            Optional<Member> optionalMember =memberRepository.findByEmailAndPassword(email, "1234");
            optionalMember.get();
        });
    }
}