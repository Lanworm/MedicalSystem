package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.component.enums.EventStatus;
import com.tsystems.javaschool.medical.backend.dao.EventRepository;
import com.tsystems.javaschool.medical.backend.dto.EventRequestDto;
import com.tsystems.javaschool.medical.backend.dto.prescriptions.PrescriptionRequestDto;
import org.quartz.CronExpression;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
public class PrescriptionCronService {
    private final EventRepository eventRepository;

    public PrescriptionCronService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    void generateEventsByPrescription(PrescriptionRequestDto prescriptionParams) {

        ArrayList<Date> timeSet = new ArrayList<>();
        Period period = Period.between(prescriptionParams.getStartDate().toLocalDateTime().toLocalDate(), prescriptionParams.getEndDate().toLocalDateTime().toLocalDate());
        int diff = period.getDays();
        try {
            for (long i = diff; i > 0; i--) {
                CronExpression cron = new CronExpression(prescriptionParams.getTimePattern());
                final Date firstExecution = cron.getNextValidTimeAfter(timeSet.isEmpty() ? prescriptionParams.getStartDate() : timeSet.get(timeSet.size() - 1));
                timeSet.add(new Date(firstExecution.getTime()));
                eventRepository.create(prepareEventRequestDto(firstExecution.getTime(), prescriptionParams));
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private static EventRequestDto prepareEventRequestDto(final long startDate, PrescriptionRequestDto prescriptionParams) {


        Calendar now = Calendar.getInstance();
        now.setTime(new Date(startDate));
        now.add(Calendar.MINUTE, 30);
        Date endDate = now.getTime();

        EventRequestDto eventRequestDto = new EventRequestDto();
        eventRequestDto.setStartDate(new Timestamp(startDate));
        eventRequestDto.setEndDate(new Timestamp(endDate.getTime()));
        eventRequestDto.setStatus(EventStatus.ASSIGNED.getValue());
        eventRequestDto.setPatientId(prescriptionParams.getPatientId());
        eventRequestDto.setProcedureId(prescriptionParams.getProcedureId());
        eventRequestDto.setStaffId(1);
        eventRequestDto.setRoomId(1);

        return eventRequestDto;
    }
}
