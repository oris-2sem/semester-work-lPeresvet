package ru.itis.judgeassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.judgeassistant.models.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
