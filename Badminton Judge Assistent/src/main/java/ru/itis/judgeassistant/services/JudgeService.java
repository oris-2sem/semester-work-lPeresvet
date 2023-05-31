package ru.itis.judgeassistant.services;

import ru.itis.judgeassistant.dto.CoachDto;
import ru.itis.judgeassistant.dto.SignInDto;
import ru.itis.judgeassistant.dto.judge.*;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.Team;
import ru.itis.judgeassistant.models.users.Judge;
import ru.itis.judgeassistant.repositories.JudgeRepository;
import ru.itis.judgeassistant.repositories.TeamRepository;

public interface JudgeService {
    JudgeDtoWithId save(SignInDto newJudge);
    JudgeDtoWithId findById(Long id);
    JudgeDtoWithId findByLogin(String login);
    JudgeDtoWithId update(Long judgeId, JudgeDto judgeDto);
    JudgeDtoWithPassword updatePassword(Long judgeId);
    void delete(Long judgeId);

    JudgesPageWithPasswords create(Integer quantity);

    static Judge getOrElseThrow(Long judgeId, JudgeRepository judgeRepository) {
        return judgeRepository.findById(judgeId)
                .orElseThrow(() -> new NotFoundException("No judge with id: <" + judgeId + "> found"));
    }
}
