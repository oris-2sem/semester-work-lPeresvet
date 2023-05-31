package ru.itis.judgeassistant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.judgeassistant.dto.score.ScoreDto;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.Game;
import ru.itis.judgeassistant.models.Score;
import ru.itis.judgeassistant.repositories.GameRepository;
import ru.itis.judgeassistant.repositories.ScoreRepository;
import ru.itis.judgeassistant.services.ScoreService;
import ru.itis.judgeassistant.services.converters.mapstruct.ScoreMapper;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {
    private final ScoreRepository scoreRepository;
    private final GameRepository gameRepository;
    private final ScoreMapper scoreMapper;

    @Override
    public ScoreDto create() {
        Score score = new Score();
        score.setFirstTeamPoints(0);
        score.setSecondTeamPoints(0);
        score.setStatus(Score.Status.ACTIVE);

        scoreRepository.save(score);

        return scoreMapper.fromScore(score);
    }

    @Override
    public ScoreDto get(Long id) {
        Score score = getOrElseThrow(id);

        return scoreMapper.fromScore(score);
    }

    @Override
    public ScoreDto incrementFirst(Long id) {
        Score score = getOrElseThrow(id);

        Integer points = score.getFirstTeamPoints();
        points++;
        score.setFirstTeamPoints(points);
        scoreRepository.save(score);

        Game game = score.getGame();
        game.setTurn((byte) 1);
        gameRepository.save(game);

        return scoreMapper.fromScore(score);
    }

    @Override
    public ScoreDto incrementSecond(Long id) {
        Score score = getOrElseThrow(id);

        Integer points = score.getSecondTeamPoints();
        points++;
        score.setSecondTeamPoints(points);
        scoreRepository.save(score);

        Game game = score.getGame();
        game.setTurn((byte) 2);
        gameRepository.save(game);

        return scoreMapper.fromScore(score);
    }

    @Override
    public ScoreDto decrementFirst(Long id) {
        Score score = getOrElseThrow(id);

        Integer points = score.getFirstTeamPoints();
        if (points > 0) {
            points--;
            score.setFirstTeamPoints(points);
            scoreRepository.save(score);
        }

        return scoreMapper.fromScore(score);
    }

    @Override
    public ScoreDto decrementSecond(Long id) {
        Score score = getOrElseThrow(id);

        Integer points = score.getSecondTeamPoints();
        if (points > 0) {
            points--;
            score.setSecondTeamPoints(points);
            scoreRepository.save(score);
        }

        return scoreMapper.fromScore(score);
    }

    @Override
    public ScoreDto storeScore(Long id) {
        Score score = getOrElseThrow(id);

        score.setStatus(Score.Status.STORED);

        scoreRepository.save(score);
        return scoreMapper.fromScore(score);
    }

    @Override
    public void delete(Long id) {
        Score score = getOrElseThrow(id);
        scoreRepository.delete(score);
    }

    private Score getOrElseThrow(Long scoreId) {
        return scoreRepository.findById(scoreId)
                .orElseThrow(() -> new NotFoundException("No score with id: <" + scoreId + "> found"));
    }
}
