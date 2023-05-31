package ru.itis.judgeassistant.services;

import ru.itis.judgeassistant.dto.score.ScoreDto;

public interface ScoreService {
    ScoreDto create();
    ScoreDto get(Long id);
    ScoreDto incrementFirst(Long id);
    ScoreDto incrementSecond(Long id);
    ScoreDto decrementFirst(Long id);
    ScoreDto decrementSecond(Long id);

    ScoreDto storeScore(Long id);
    void delete(Long id);
}
