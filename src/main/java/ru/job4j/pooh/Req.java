package ru.job4j.pooh;

import java.util.Objects;

public class Req {
    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        String[] str = content.split(System.lineSeparator());
        String httpRequestType = str[0].split("\\s")[0];
        String poohMode = str[0].split("/")[1];
        String sourceName = str[0].split("/")[2].split("\\s")[0];
        String param = "";
        if (Objects.equals(httpRequestType, "GET") && Objects.equals(poohMode, "topic")) {
            param = str[0].split("/")[3].split("\\s")[0];
        }
        if (Objects.equals(httpRequestType, "POST")) {
            param = str[str.length - 1];
        }
        return new Req(httpRequestType, poohMode, sourceName, param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}
