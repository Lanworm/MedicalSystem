package com.tsystems.javaschool.medical.backend.component;

import com.tsystems.javaschool.medical.backend.EventStatusChangerExeption;

public interface EventStatusChanger {

    void changeStatus(int eventId , String newStatus) throws EventStatusChangerExeption;

}
