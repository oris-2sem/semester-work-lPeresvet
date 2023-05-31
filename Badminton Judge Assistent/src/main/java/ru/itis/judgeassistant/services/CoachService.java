package ru.itis.judgeassistant.services;

import ru.itis.judgeassistant.dto.CoachDto;
import ru.itis.judgeassistant.models.simpleentities.Coach;

public interface CoachService {
    CoachDto save(CoachDto newCoach);
    CoachDto findById(Long id);
    CoachDto update(Long coachId, CoachDto coachUpdate);
    void delete(Long coachId);


}
