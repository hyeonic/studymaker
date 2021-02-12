package me.hyeonic.studymaker.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudyTest {

    @Test
    @DisplayName("정적 팩토리 메소드를 확인하는 테스트")
    void createStudy() {
        
        // given
        String nameA = "Java";
        String nameB = "Spring";

        // when
        Study studyA = Study.createStudy(nameA);
        Study studyB = Study.createStudy(nameB);

        // then
        assertThat(studyA.getName()).isEqualTo(nameA);
        assertThat(studyB.getName()).isEqualTo(nameB);
    }

    @Test
    @DisplayName("study 의 이름을 변경하는 메소드를 확인하는 테스트")
    void changeStudyName() {

        // given
        Study studyA = Study.createStudy("Java");

        // when
        String changedName = "Java and Spring";
        studyA.changeStudyName(changedName);

        // then
        assertThat(studyA.getName()).isEqualTo(changedName);
    }
    
    @Test
    @DisplayName("study 에 member 를 추가하는 테스트")
    void withdrawStudy() {
        
        // given
        Member memberA = Member.createMember("a@aaa.com", "123");
        Member memberB = Member.createMember("b@bbb.com", "123");

        Study study = Study.createStudy("Java");

        // when
        StudyItem studyItemA = StudyItem.selectStudy(memberA, study); // study 에 studyItem 추가
        StudyItem studyItemB = StudyItem.selectStudy(memberB, study); // study 에 studyItem 추가

        List<StudyItem> studyItems = study.getStudyItems();

        // then
        Assertions.assertThat(study.getStudyItems().size()).isEqualTo(2);
    }
}