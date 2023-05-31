package ru.itis.judgeassistant.dto.team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TeamsPage {
    private List<TeamDto> teams;
    private Integer teamsNum;
}
