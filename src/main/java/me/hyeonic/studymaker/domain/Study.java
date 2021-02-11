package me.hyeonic.studymaker.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Study extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "study_id")
    private Long id;

    private String name;
}
