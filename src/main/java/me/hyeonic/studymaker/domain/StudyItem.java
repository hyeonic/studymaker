package me.hyeonic.studymaker.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "study_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    private StudyItem(Member member, Study study) {
        this.member = member;
        this.study = study;
    }

    //== 정적 팩토리 메소드 ==//
    public static StudyItem selectStudy(Member member, Study study) {
        StudyItem studyItem = new StudyItem(member, study);
        study.addStudyItem(studyItem);
        return studyItem;
    }
}