package com.tsystems.javaschool.medical.backend.enums;

public enum EventsSort {

    SUNDAY ("patientByPatientId", "patient"),
    MONDAY ("proceduresByProcedureId", "procedure"),
    TUESDAY ("roomsByRoomId", "room"),
    WEDNESDAY ("Среда", "awda"),
    THURSDAY ("Четверг", "awda"),
    FRIDAY ("Пятница", "awda"),
    SATURDAY ("Суббота", "awda");

    EventsSort(String entityName , String uiName) {

    }
}
