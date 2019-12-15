package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.PrescriptionRepository;
import com.tsystems.javaschool.medical.backend.dto.prescriptions.PrescriptionDto;
import com.tsystems.javaschool.medical.backend.dto.prescriptions.PrescriptionRequestDto;
import com.tsystems.javaschool.medical.backend.entities.PrescriptionsEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.tsystems.javaschool.medical.backend.mapper.PrescriptionMapper.PRESCRIPTION_MAPPER;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionCronService prescriptionCronService;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, PrescriptionCronService prescriptionCronService) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionCronService = prescriptionCronService;
    }

    public List<PrescriptionDto> getAll() {
        List<PrescriptionsEntity> prescriptionsEntities = (List<PrescriptionsEntity>) prescriptionRepository.findAll();
        List<PrescriptionDto> prescriptionDtos = new ArrayList<>();
        for (PrescriptionsEntity entity : prescriptionsEntities) {
            PrescriptionDto prescriptionDto =  PRESCRIPTION_MAPPER.fromPrescription(entity);
            prescriptionDtos.add(prescriptionDto);
        }
        return prescriptionDtos;
    }

    public List<PrescriptionDto> getListByPatient(int id) {
        List<PrescriptionsEntity> prescriptionsEntities = prescriptionRepository.getByUserId(id);
        List<PrescriptionDto> prescriptionDtos = new ArrayList<>();
        for (PrescriptionsEntity entity : prescriptionsEntities) {
            PrescriptionDto prescriptionDto = PRESCRIPTION_MAPPER.fromPrescription(entity);
            prescriptionDtos.add(prescriptionDto);
        }
        return prescriptionDtos;
    }

    public void save (PrescriptionRequestDto params){
        PrescriptionsEntity prescriptionsEntity = PRESCRIPTION_MAPPER.toPrescription(params);
        prescriptionRepository.save(prescriptionsEntity);
        prescriptionCronService.generateEventsByPrescription(params);
    }
}
