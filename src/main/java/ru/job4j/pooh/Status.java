package ru.job4j.pooh;

public enum Status {
    OK("200"),
    SERVER_ERROR("500");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
