package ru.itis.judgeassistant.services.helpers;

import ru.itis.judgeassistant.dto.judge.JudgeDtoWithPassword;
import ru.itis.judgeassistant.dto.judge.JudgesPageWithPasswords;

public class MessageCreationHelper {
    public static String createMessage(JudgesPageWithPasswords judgesPageWithPasswords) {
        StringBuilder result = new StringBuilder();
        for (JudgeDtoWithPassword judge: judgesPageWithPasswords.getJudges()) {
            String str = judge.getLogin() + " " + judge.getPassword() + "%0A";
            result.append(str);
        }
        return result.toString();
    }
}
