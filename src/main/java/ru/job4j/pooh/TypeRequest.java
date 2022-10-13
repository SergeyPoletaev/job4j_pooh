package ru.job4j.pooh;

enum TypeRequest {
    POST("POST"),
    GET("GET");

    private final String value;

    TypeRequest(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
