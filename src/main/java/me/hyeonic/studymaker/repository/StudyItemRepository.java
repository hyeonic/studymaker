package me.hyeonic.studymaker.repository;

import me.hyeonic.studymaker.domain.StudyItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyItemRepository extends JpaRepository<StudyItem, Long> {
}