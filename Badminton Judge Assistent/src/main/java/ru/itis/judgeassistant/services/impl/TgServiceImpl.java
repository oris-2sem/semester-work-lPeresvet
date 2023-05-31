package ru.itis.judgeassistant.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import ru.itis.judgeassistant.services.TgService;
import ru.itis.judgeassistant.services.helpers.exceptions.APIResponseException;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class TgServiceImpl implements TgService {
    private static final String API_URL = "https://api.telegram.org/bot";
    private static final String API_TOKEN = "6167317222:AAFj010qWZz2PRM0n5rKKfIdAu8A5Nmc8Ho";

    private final OkHttpClient client;


    @Override
    public void sendMessage(String message) {
        Set<Integer> ids = getChatIds();
        for (Integer id : ids) {

            Request request = new Request.Builder()
                    .url(buildMessageUrl(id, message))
                    .build();
            Call call = client.newCall(request);
            Response response;
            try {
                response = call.execute();
            } catch (IOException e) {
                log.warn(API_URL + " некоректно ответил");
                throw new APIResponseException(API_URL + " некоректно ответил");
            }

            try {
                log.debug(response.body().string());
            } catch (IOException e) {
                log.warn(API_URL + " некоректно ответил");
                throw new RuntimeException(e);
            }
        }

    }

    private Set<Integer> getChatIds() {
        Set<Integer> result = new HashSet<>();
        Request request = new Request.Builder()
                .url(API_URL + API_TOKEN + "/getUpdates")
                .build();
        Call call = client.newCall(request);
        Response response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new APIResponseException(API_URL + " некоректно ответил");
        }

        try {
            String json = response.body().string();
            ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
            JsonNode jsonNode = node.get("result");
            for (Iterator<JsonNode> it = jsonNode.elements(); it.hasNext(); ) {
                JsonNode curNode = it.next();
                if (curNode.has("message")) {
                    result.add(curNode.get("message").get("chat").get("id").asInt());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
        return result;
    }

    private String buildMessageUrl(Integer id, String message) {
        return API_URL + API_TOKEN + "/sendMessage?chat_id=" + id + "&text=" + message;
    }
}
