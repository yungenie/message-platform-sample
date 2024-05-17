package com.exflyer.messageplatformsample.common.utils;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class GsonUtil {

    private final Gson gson;

    public GsonUtil() {
        this.gson = new Gson();
    }

    public Gson getGson() {
        return gson;
    }

    public <T> String getPrettyGsonStr(T object) {
        return gson.newBuilder().setPrettyPrinting().create().toJson(object);
    }

}
