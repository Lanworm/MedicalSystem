package com.tsystems.javaschool.medical.backend.component.enums;

public enum EventStatus {

    ASSIGNED("ASSIGNED"),
    IN_WORK("IN_WORK"),
    DONE("DONE"),
    CANCELED("CANCELED");

    private String value;

    EventStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
