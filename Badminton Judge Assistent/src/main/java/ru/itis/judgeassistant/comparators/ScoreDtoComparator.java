package ru.itis.judgeassistant.comparators;

import ru.itis.judgeassistant.dto.score.ScoreDto;

import java.util.Comparator;

public class ScoreDtoComparator implements Comparator<ScoreDto> {

    @Override
    public int compare(ScoreDto score1, ScoreDto score2) {
        return score1.getId().compareTo(score2.getId());
    }
}
