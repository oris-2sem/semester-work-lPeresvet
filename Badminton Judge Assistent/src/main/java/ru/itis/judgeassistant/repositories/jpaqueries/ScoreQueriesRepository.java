package ru.itis.judgeassistant.repositories.jpaqueries;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.judgeassistant.models.Score;

import java.util.List;

public interface ScoreQueriesRepository {
    List<Score> getScoreOnCort(Integer cort);
}
