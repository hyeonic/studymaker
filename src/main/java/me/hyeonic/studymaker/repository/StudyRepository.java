package me.hyeonic.studymaker.repository;

import me.hyeonic.studymaker.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long> {

    Optional<Study> findByName(String name);
}