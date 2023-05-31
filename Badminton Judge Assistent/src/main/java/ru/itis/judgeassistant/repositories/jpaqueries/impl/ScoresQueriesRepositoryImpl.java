package ru.itis.judgeassistant.repositories.jpaqueries.impl;

import org.springframework.stereotype.Repository;
import ru.itis.judgeassistant.models.Score;
import ru.itis.judgeassistant.repositories.jpaqueries.ScoreQueriesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ScoresQueriesRepositoryImpl implements ScoreQueriesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Score> getScoreOnCort(Integer cort) {
        return null;
    }
}
