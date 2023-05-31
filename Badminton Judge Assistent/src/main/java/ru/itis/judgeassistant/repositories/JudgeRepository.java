package ru.itis.judgeassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.judgeassistant.models.users.Judge;

import java.util.Optional;

public interface JudgeRepository extends JpaRepository<Judge, Long> {
    Optional<Judge> findByLogin(String username);
}
