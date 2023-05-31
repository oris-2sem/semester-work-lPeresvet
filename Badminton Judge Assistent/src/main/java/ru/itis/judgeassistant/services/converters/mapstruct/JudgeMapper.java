package ru.itis.judgeassistant.services.converters.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.judgeassistant.dto.SignInDto;
import ru.itis.judgeassistant.dto.judge.JudgeDto;
import ru.itis.judgeassistant.dto.judge.JudgeDtoWithId;
import ru.itis.judgeassistant.dto.judge.JudgeDtoWithPassword;
import ru.itis.judgeassistant.dto.judge.JudgeSignUpDto;
import ru.itis.judgeassistant.models.users.Judge;

@Mapper(componentModel = "spring")
public interface JudgeMapper {
    JudgeDto fromJudge(Judge judge);

//    @Mapping(target = "games", ignore = true)
//    Judge fromSignUp(JudgeSignUpDto signUpDto);

    @Mapping(target = "games", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "hashPassword", ignore = true)
    Judge fromSignIn(SignInDto signInDto);

    JudgeDtoWithId fromJudgeWithId(Judge judge);
}
