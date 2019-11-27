package com.tsystems.javaschool.medical.backend.services;

import org.quartz.CronExpression;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

@Service
public class PrescriptionCronService {

    void generateEventsByPrescription(String cronExpression, int dosage, ArrayList<Date> timeSet) {

        try {
            if(dosage>0) {
                CronExpression cron = new CronExpression(cronExpression);
                final Date firstExecution = cron.getNextValidTimeAfter(timeSet.isEmpty() ? new Date(System.currentTimeMillis()) : timeSet.get(timeSet.size() - 1));
                timeSet.add(new Date(firstExecution.getTime()));
                generateEventsByPrescription(cronExpression, dosage-1, timeSet);
            }

        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
