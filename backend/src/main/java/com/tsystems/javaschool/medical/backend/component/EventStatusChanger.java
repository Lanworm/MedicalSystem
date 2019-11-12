package com.tsystems.javaschool.medical.backend.component;

import com.tsystems.javaschool.medical.backend.CustomExeption;

public interface EventStatusChanger {

    void changeStatus(int eventId, String newStatus) throws CustomExeption;

}
