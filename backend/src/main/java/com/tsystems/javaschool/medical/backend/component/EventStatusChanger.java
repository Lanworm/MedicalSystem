package com.tsystems.javaschool.medical.backend.component;

import com.tsystems.javaschool.medical.backend.exception.EventStatusChangerExeption;

import java.math.BigInteger;

public interface EventStatusChanger {

    void changeStatus(BigInteger eventId , String newStatus) throws EventStatusChangerExeption;

}
