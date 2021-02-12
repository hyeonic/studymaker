package me.hyeonic.studymaker.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "study_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "study")
    private List<StudyItem> studyItems = new ArrayList<>();

    private Study(String name) {
        this.name = name;
    }

    //== 정적 팩토리 메소드 ==//
    public static Study createStudy(String name) {
        Study study = new Study(name);
        return study;
    }

    //== study member 추가를 위한 studyItem 을 추가하는 메소드 ==//
    //== studyItem 생성 당시에 추가된다. ==//
    public void addStudyItem(StudyItem studyItem) {
        this.studyItems.add(studyItem);
    }

    //== study 이름 변경 ==//
    public void changeStudyName(String name) {
        this.name = name;
    }

    //== study 탈퇴 ==//
    public void withdrawStudy(Member member) {
        int size = studyItems.size();
        for (int i = 0; i < studyItems.size(); i++) {
            if (studyItems.get(i).getMember().getId() == member.getId()) {
                studyItems.remove(i);
                break;
            }
        }

        if (size == studyItems.size()) { // 기존과 member 의 수가 동일하면, 존재하지 않는 멤버라고 간주한다.
            throw new IllegalStateException("해당 study 에 존재하지 않는 member 입니다.");
        }
    }
}