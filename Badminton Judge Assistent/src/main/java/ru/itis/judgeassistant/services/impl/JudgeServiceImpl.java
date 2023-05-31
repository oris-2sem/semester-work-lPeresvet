package ru.itis.judgeassistant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.judgeassistant.dto.SignInDto;
import ru.itis.judgeassistant.dto.judge.JudgeDto;
import ru.itis.judgeassistant.dto.judge.JudgeDtoWithId;
import ru.itis.judgeassistant.dto.judge.JudgeDtoWithPassword;
import ru.itis.judgeassistant.dto.judge.JudgesPageWithPasswords;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.users.Judge;
import ru.itis.judgeassistant.models.users.User;
import ru.itis.judgeassistant.repositories.JudgeRepository;
import ru.itis.judgeassistant.services.JudgeService;
import ru.itis.judgeassistant.services.TgService;
import ru.itis.judgeassistant.services.converters.mapstruct.JudgeMapper;
import ru.itis.judgeassistant.services.helpers.FileCreationHelper;
import ru.itis.judgeassistant.services.helpers.MessageCreationHelper;
import ru.itis.judgeassistant.services.helpers.PasswordGenerationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService {
    private final JudgeRepository judgeRepository;
    private final JudgeMapper judgeMapper;
    private final PasswordEncoder passwordEncoder;
    private final TgService tgService;


    @Override
    public JudgeDtoWithId save(SignInDto newJudge) {
        Judge judge = judgeMapper.fromSignIn(newJudge);
        judge.setHashPassword(passwordEncoder.encode(newJudge.getPassword()));

        judge.setRole(User.Role.JUDGE);
        judge.setState(User.State.CONFIRMED);

        judgeRepository.save(judge);
        return judgeMapper.fromJudgeWithId(judge);
    }

    @Override
    public JudgeDtoWithId findById(Long id) {
        Judge judge = JudgeService.getOrElseThrow(id, judgeRepository);
        return judgeMapper.fromJudgeWithId(judge);
    }

    @Override
    public JudgeDtoWithId findByLogin(String login) {
        Judge judge = judgeRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("Не найдено пользователя с логином <" + login + ">"));

        return judgeMapper.fromJudgeWithId(judge);
    }

    @Override
    public JudgeDtoWithId update(Long judgeId, JudgeDto judgeDto) {
        Judge judge = JudgeService.getOrElseThrow(judgeId, judgeRepository);

        judge.setName(judgeDto.getName());
        judge.setSurname(judgeDto.getSurname());
        judge.setLogin(judgeDto.getLogin());
        judgeRepository.save(judge);

        return judgeMapper.fromJudgeWithId(judge);
    }

    @Override
    public JudgeDtoWithPassword updatePassword(Long judgeId) {
        Judge judge = JudgeService.getOrElseThrow(judgeId, judgeRepository);
        String pswd = PasswordGenerationHelper.getPassword();
        judge.setHashPassword(passwordEncoder.encode(pswd));

        judgeRepository.save(judge);

        return JudgeDtoWithPassword.builder()
                .password(pswd)
                .login(judge.getLogin())
                .build();
    }


    @Override
    public void delete(Long judgeId) {
        Judge judge = JudgeService.getOrElseThrow(judgeId, judgeRepository);
        judgeRepository.delete(judge);
    }

    @Override
    public JudgesPageWithPasswords create(Integer quantity) {
        List<JudgeDtoWithPassword> result = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            String id = UUID.randomUUID().toString().split("-")[0];
            String pswd = PasswordGenerationHelper.getPassword();
            SignInDto signInDto = SignInDto.builder()
                    .login("judge-" + id)
                    .password(pswd)
                    .build();
            save(signInDto);
            JudgeDtoWithPassword judgeDtoWithPassword = JudgeDtoWithPassword.builder()
                    .login("judge-" + id)
                    .password(pswd)
                    .build();
            result.add(judgeDtoWithPassword);
        }

        JudgesPageWithPasswords judges = JudgesPageWithPasswords.builder()
                .judges(result)
                .judgesNum(result.size())
                .build();

        tgService.sendMessage(MessageCreationHelper.createMessage(judges));
        return judges;

    }

}
