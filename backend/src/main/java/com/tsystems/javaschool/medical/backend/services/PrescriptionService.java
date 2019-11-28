package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.PrescriptionRepository;
import com.tsystems.javaschool.medical.backend.dto.PrescriptionDto;
import com.tsystems.javaschool.medical.backend.entities.PrescriptionsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final ModelMapper modelMapper;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, ModelMapper modelMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.modelMapper = modelMapper;
    }

    public List<PrescriptionDto> getAll() {
        List<PrescriptionsEntity> prescriptionsEntities = (List<PrescriptionsEntity>) prescriptionRepository.findAll();
        List<PrescriptionDto> prescriptionDtos = new ArrayList<>();
        for (Object entity : prescriptionsEntities) {
            PrescriptionDto prescriptionDto = modelMapper.map(entity, PrescriptionDto.class);
            prescriptionDtos.add(prescriptionDto);
        }
        return prescriptionDtos;
    }

    public List<PrescriptionDto> getListByPatient(int id) {
        List<PrescriptionsEntity> prescriptionsEntities = prescriptionRepository.getByUserId(id);
        List<PrescriptionDto> prescriptionDtos = new ArrayList<>();
        for (Object entity : prescriptionsEntities) {
            PrescriptionDto prescriptionDto = modelMapper.map(entity, PrescriptionDto.class);
            prescriptionDtos.add(prescriptionDto);
        }
        return prescriptionDtos;
    }

}
