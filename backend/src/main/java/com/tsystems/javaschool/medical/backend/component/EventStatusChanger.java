package com.tsystems.javaschool.medical.backend.component;

import com.tsystems.javaschool.medical.backend.exception.EventStatusChangerExeption;

public interface EventStatusChanger {

    void changeStatus(int eventId , String newStatus) throws EventStatusChangerExeption;

}
