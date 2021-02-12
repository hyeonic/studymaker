package me.hyeonic.studymaker.repository;

import me.hyeonic.studymaker.domain.Member;
import me.hyeonic.studymaker.domain.Study;
import me.hyeonic.studymaker.domain.StudyItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class StudyRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired StudyRepository studyRepository;
    @Autowired StudyItemRepository studyItemRepository;

    @Test
    @DisplayName("study 를 생성하여 저장하는 테스트")
    void saveStudy() {

        // given
        Study study = Study.createStudy("Java"); // study 생성
        Study savedStudy = studyRepository.save(study);
        
        // when
        Study findStudy = studyRepository.findById(savedStudy.getId()).get();

        // then
        Assertions.assertThat(findStudy.getId()).isEqualTo(savedStudy.getId());
        Assertions.assertThat(findStudy.getName()).isEqualTo(savedStudy.getName());
    }

    @Test
    @DisplayName("study 의 name 으로 조회하는 테스트")
    void findByName() {

        // given
        Study study = Study.createStudy("Java"); // study 생성
        Study savedStudy = studyRepository.save(study);

        // when
        Optional<Study> findStudy = studyRepository.findByName("Java");// "Java"로 조회

        // then
        Assertions.assertThat(findStudy.get().getId()).isEqualTo(savedStudy.getId());
        Assertions.assertThat(findStudy.get().getName()).isEqualTo(savedStudy.getName());
    }
    
    @Test
    @DisplayName("study 에 member 를 등록하는 테스트")
    void inviteMember() {
        
        // given
        Member memberA = Member.createMember("a@aaa.com", "123");
        Member memberB = Member.createMember("b@bbb.com", "123");
        Member savedMemberA = memberRepository.save(memberA); // 영속성 추가
        Member savedMemberB = memberRepository.save(memberB); // 영속성 추가

        Study study = Study.createStudy("Java"); // study 생성
        Study savedStudy = studyRepository.save(study); // 영속성 추가

        // when
        StudyItem studyItemA = StudyItem.selectStudy(memberA, study); // study 에 studyItem 추가
        StudyItem studyItemB = StudyItem.selectStudy(memberB, study); // study 에 studyItem 추가
        StudyItem savedStudyItemA = studyItemRepository.save(studyItemA);// 영속성 추가
        StudyItem savedStudyItemB = studyItemRepository.save(studyItemB); // 영속성 추가

        // then
        Assertions.assertThat(savedStudy.getStudyItems().size()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("study 의 member 한명을 탈퇴하는 테스트")
    void deleteMemberInStudy() {

        // given
        Member memberA = Member.createMember("a@aaa.com", "123");
        Member memberB = Member.createMember("b@bbb.com", "123");
        Member savedMemberA = memberRepository.save(memberA); // 영속성 추가
        Member savedMemberB = memberRepository.save(memberB); // 영속성 추가
        
        Study study = Study.createStudy("Java");
        Study savedStudy = studyRepository.save(study); // 영속성 추가

        StudyItem studyItemA = StudyItem.selectStudy(memberA, study); // study 에 studyItem 추가
        StudyItem studyItemB = StudyItem.selectStudy(memberB, study); // study 에 studyItem 추가
        StudyItem savedStudyItemA = studyItemRepository.save(studyItemA);// 영속성 추가
        StudyItem savedStudyItemB = studyItemRepository.save(studyItemB); // 영속성 추가

        // when
        savedStudy.withdrawStudy(savedMemberA); // study 에서 memberA 삭제
        studyItemRepository.delete(savedStudyItemA); // studyItemA 삭제

        // then
        Assertions.assertThat(savedStudy.getStudyItems().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("study 에 존재하지 않는 member 한명을 탈퇴 시도를 하는데 실패하는 테스트")
    void deleteMemberInStudy_fail() {

        // given
        Member memberA = Member.createMember("a@aaa.com", "123");
        Member memberB = Member.createMember("b@bbb.com", "123");
        Member memberC = Member.createMember("c@ccc.com", "123"); // 탈퇴 실패를 위한 member
        Member savedMemberA = memberRepository.save(memberA); // 영속성 추가
        Member savedMemberB = memberRepository.save(memberB); // 영속성 추가
        Member savedMemberC = memberRepository.save(memberC); // 영속성 추가

        // when
        Study study = Study.createStudy("Java");
        Study savedStudy = studyRepository.save(study); // 영속성 추가

        StudyItem studyItemA = StudyItem.selectStudy(memberA, study); // study 에 studyItem 추가
        StudyItem studyItemB = StudyItem.selectStudy(memberB, study); // study 에 studyItem 추가
        StudyItem savedStudyItemA = studyItemRepository.save(studyItemA);// 영속성 추가
        StudyItem savedStudyItemB = studyItemRepository.save(studyItemB); // 영속성 추가

        // then
        assertThrows(IllegalStateException.class, () ->
                savedStudy.withdrawStudy(savedMemberC) // study 에서 memberC 삭제 시도
        );
    }
}