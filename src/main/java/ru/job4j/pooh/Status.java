package ru.job4j.pooh;

public enum Status {
    OK("200"),
    NO_CONTENT("204"),
    NOT_IMPLEMENTED("501");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
