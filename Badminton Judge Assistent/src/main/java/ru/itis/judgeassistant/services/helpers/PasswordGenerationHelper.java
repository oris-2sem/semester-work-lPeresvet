package ru.itis.judgeassistant.services.helpers;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.itis.judgeassistant.services.helpers.exceptions.APIResponseException;

import java.io.IOException;

public class PasswordGenerationHelper {
    private static final String API_URL = "https://www.passwordrandom.com";
    private static final String PASSWORD_URL = "/query?command=password";

    private static final OkHttpClient client = new OkHttpClient();
    public static String getPassword() {
        Request request = new Request.Builder()
                .url(API_URL + PASSWORD_URL)
                .build();

        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new APIResponseException(API_URL + PASSWORD_URL + " некоректно ответил");
        }
        if (response.isSuccessful()) {
            try {
                if (response.body() != null) {
                    return response.body().string();
                }
            } catch (IOException e) {
                throw new APIResponseException(API_URL + PASSWORD_URL + " некоректно ответил");
            }
        }
        throw new APIResponseException(API_URL + PASSWORD_URL + " ответил с ошибкой");

    }
}
