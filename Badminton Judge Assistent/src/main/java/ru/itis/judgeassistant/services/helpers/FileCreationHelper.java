package ru.itis.judgeassistant.services.helpers;

import ru.itis.judgeassistant.dto.judge.JudgeDtoWithPassword;
import ru.itis.judgeassistant.dto.judge.JudgesPageWithPasswords;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileCreationHelper {
    public static boolean createFile(JudgesPageWithPasswords judgesPageWithPasswords) {
        try (PrintWriter printWriter = new PrintWriter("data.txt")){
            for (JudgeDtoWithPassword judge: judgesPageWithPasswords.getJudges()) {
                String result = judge.getLogin() + " " + judge.getPassword();
                printWriter.println(result);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
}
