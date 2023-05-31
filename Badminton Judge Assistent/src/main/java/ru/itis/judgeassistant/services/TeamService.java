package ru.itis.judgeassistant.services;

import ru.itis.judgeassistant.dto.player.NewPlayersDto;
import ru.itis.judgeassistant.dto.team.NewTeamDto;
import ru.itis.judgeassistant.dto.team.TeamDto;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.Team;
import ru.itis.judgeassistant.repositories.TeamRepository;

public interface TeamService {
    TeamDto save(NewTeamDto newTeam);
    TeamDto save(NewPlayersDto newTeam);
    TeamDto findById(Long id);
    TeamDto update(Long teamId, NewTeamDto teamDto);
    void delete(Long teamId);
    static Team getOrElseThrow(Long teamId, TeamRepository teamRepository) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundException("No team with id: <" + teamId + "> found"));
    }
}
