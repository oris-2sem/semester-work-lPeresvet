package ru.itis.judgeassistant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.judgeassistant.dto.player.NewPlayersDto;
import ru.itis.judgeassistant.dto.player.PlayerDto;
import ru.itis.judgeassistant.dto.team.NewTeamDto;
import ru.itis.judgeassistant.dto.team.TeamDto;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.Team;
import ru.itis.judgeassistant.models.simpleentities.Player;
import ru.itis.judgeassistant.repositories.PlayerRepository;
import ru.itis.judgeassistant.repositories.TeamRepository;
import ru.itis.judgeassistant.services.PlayerService;
import ru.itis.judgeassistant.services.TeamService;
import ru.itis.judgeassistant.services.converters.mapstruct.PlayerMapper;
import ru.itis.judgeassistant.services.converters.mapstruct.TeamMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final PlayerService playerService;
    private final TeamMapper teamMapper;
    private final PlayerMapper playerMapper;

    @Override
    public TeamDto save(NewTeamDto newTeam) {
        Team team = new Team();
        teamRepository.save(team);

        List<Player> players = getPlayers(newTeam);
        team.setPlayers(players);

        teamRepository.save(team);

        return teamMapper.fromTeam(team);
    }

    @Override
    public TeamDto save(NewPlayersDto newTeam) {
        Team team = new Team();
        teamRepository.save(team);

        List<Player> players = new ArrayList<>();
        for(PlayerDto playerDto : newTeam.getPlayers()) {
            Player player = playerRepository.save(playerMapper.fromDto(playerDto));
            players.add(player);
        }
        team.setPlayers(players);

        teamRepository.save(team);

        return teamMapper.fromTeam(team);
    }

    @Override
    public TeamDto findById(Long id) {
        Team team = getOrElseThrow(id);
        return teamMapper.fromTeam(team);
    }

    @Override
    public TeamDto update(Long teamId, NewTeamDto teamDto) {
        Team team = getOrElseThrow(teamId);

        List<Player> players = getPlayers(teamDto);

        team.setPlayers(players);
        teamRepository.save(team);

        return teamMapper.fromTeam(team);
    }

    @Override
    public void delete(Long teamId) {
        Team team = getOrElseThrow(teamId);

        teamRepository.delete(team);
    }

    private List<Player> getPlayers(NewTeamDto teamDto) {
        List<Player> players = new ArrayList<>();
        for (Long playerId : teamDto.getPlayers()) {
            Player player = PlayerService.getOrElseThrow(playerId, playerRepository);
            players.add(player);
        }
        return players;
    }

    private Team getOrElseThrow(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundException("No team with id: <" + teamId + "> found"));
    }
}
