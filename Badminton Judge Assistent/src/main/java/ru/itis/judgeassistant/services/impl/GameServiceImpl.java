package ru.itis.judgeassistant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.judgeassistant.comparators.ScoreDtoComparator;
import ru.itis.judgeassistant.dto.game.GameDto;
import ru.itis.judgeassistant.dto.game.GamesPage;
import ru.itis.judgeassistant.dto.game.NewGameDto;
import ru.itis.judgeassistant.dto.game.PlayersScoresDto;
import ru.itis.judgeassistant.dto.score.ScoreDto;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.Game;
import ru.itis.judgeassistant.models.Score;
import ru.itis.judgeassistant.models.Team;
import ru.itis.judgeassistant.repositories.*;
import ru.itis.judgeassistant.services.CortService;
import ru.itis.judgeassistant.services.GameService;
import ru.itis.judgeassistant.services.JudgeService;
import ru.itis.judgeassistant.services.TeamService;
import ru.itis.judgeassistant.services.converters.mapstruct.GameMapper;
import ru.itis.judgeassistant.services.converters.mapstruct.ScoreMapper;
import ru.itis.judgeassistant.services.converters.mapstruct.TeamMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final JudgeRepository judgeRepository;
    private final CortRepository cortRepository;
    private final ScoreRepository scoreRepository;
    private final GameMapper gameMapper;
    private final ScoreMapper scoreMapper;
    private final TeamMapper teamMapper;

    @Override
    public GameDto create(NewGameDto newGame) {
        Game game = gameMapper.fromNewDto(newGame);

        game.setTime(new Date(System.currentTimeMillis()));

        List<Team> teams = getTeams(newGame);
        game.setTeams(teams);

        game.setJudge(JudgeService.getOrElseThrow(newGame.getJudgeId(), judgeRepository));

        game.setCort(CortService.getOrElseThrow(newGame.getCortId(), cortRepository));

        List<Score> scores = new ArrayList<>();
        Score score = generateScore();

        scores.add(score);

        game.setScores(scores);
        gameRepository.save(game);
        score.setGame(game);
        scoreRepository.save(score);
        return gameMapper.fromGame(game);
    }

    private Score generateScore() {
        Score score = new Score();
        score.setStatus(Score.Status.ACTIVE);
        score.setFirstTeamPoints(0);
        score.setSecondTeamPoints(0);
        return score;
    }

    @Override
    public GameDto findById(Long id) {
        Game game = getOrElseThrow(id);

        return gameMapper.fromGame(game);
    }

    @Override
    public GameDto update(Long gameId, NewGameDto newGameDto) {
        Game game = getOrElseThrow(gameId);

        game.setTurn(newGameDto.getTurn());
        game.setCort(CortService.getOrElseThrow(newGameDto.getCortId(), cortRepository));
        game.setJudge(JudgeService.getOrElseThrow(newGameDto.getJudgeId(), judgeRepository));
        game.setStatus(newGameDto.getStatus());

        List<Team> teams = getTeams(newGameDto);
        game.setTeams(teams);

        gameRepository.save(game);
        return gameMapper.fromGame(game);
    }

    private List<Team> getTeams(NewGameDto newGameDto) {
        List<Team> teams = new ArrayList<>();
        teams.add(TeamService.getOrElseThrow(newGameDto.getTeam1Id(), teamRepository));
        teams.add(TeamService.getOrElseThrow(newGameDto.getTeam2Id(), teamRepository));
        return teams;
    }

    @Override
    public void delete(Long gameId) {
        Game game = getOrElseThrow(gameId);

        gameRepository.delete(game);
    }

    @Override
    public GameDto addNewScore(Long gameId) {
        Game game = getOrElseThrow(gameId);

        Score score = generateScore();
        score.setGame(game);
        scoreRepository.save(score);

        return gameMapper.fromGame(game);
    }

    @Override
    public GameDto setStatus(Long gameId, Game.Status status) {
        Game game = getOrElseThrow(gameId);
        game.setStatus(status);
        gameRepository.save(game);
        return gameMapper.fromGame(game);
    }

    @Override
    public GamesPage getTodayGames() {

        return null;
    }

    @Override
    public GamesPage getGameByCortId(Long cortId) {
        List<GameDto> games = gameRepository.getGameByCortId(cortId)
                .stream()
                .map(gameMapper::fromGame)
                .collect(Collectors.toList());

        return GamesPage.builder()
                .games(games)
                .gamesNum(games.size())
                .build();
    }


    @Override
    public PlayersScoresDto findActiveScoreByCortId(Long cortId) {
        Game game = gameRepository
                .getFirstGameByCortIdAndStatusOrderByTimeDesc(cortId, Game.Status.STARTED);
        if (game != null) {
            List<ScoreDto> scores = new ArrayList<>(game.getScores()
                    .stream()
                    .map(scoreMapper::fromScore)
                    .toList());
            scores.sort(new ScoreDtoComparator());

            return PlayersScoresDto
                    .builder()
                    .firstTeam(teamMapper.fromTeam(game.getTeams().get(0)))
                    .secondTeam(teamMapper.fromTeam(game.getTeams().get(1)))
                    .scores(scores)
                    .turn(game.getTurn())
                    .build();
        } else {
            return null;
        }
    }


    private Game getOrElseThrow(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException("No game with id: <" + gameId + "> found"));
    }
}
