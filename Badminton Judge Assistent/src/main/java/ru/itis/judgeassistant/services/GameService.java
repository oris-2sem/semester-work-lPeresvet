package ru.itis.judgeassistant.services;

import ru.itis.judgeassistant.dto.game.GameDto;
import ru.itis.judgeassistant.dto.game.GamesPage;
import ru.itis.judgeassistant.dto.game.NewGameDto;
import ru.itis.judgeassistant.dto.game.PlayersScoresDto;
import ru.itis.judgeassistant.dto.score.ScoreDto;
import ru.itis.judgeassistant.models.Game;

import java.util.List;

public interface GameService {
    GameDto create(NewGameDto newGame);
    GameDto findById(Long id);
    GameDto update(Long gameId, NewGameDto newGameDto);
    void delete(Long gameId);

    GameDto addNewScore(Long gameId);

    GameDto setStatus(Long gameId, Game.Status status);

    GamesPage getTodayGames();
    GamesPage getGameByCortId(Long cortId);

    PlayersScoresDto findActiveScoreByCortId(Long cortId);
}
