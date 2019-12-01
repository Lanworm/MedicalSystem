package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.DrugRepository;
import com.tsystems.javaschool.medical.backend.dao.PatientRepository;
import com.tsystems.javaschool.medical.backend.dao.PrescriptionRepository;
import com.tsystems.javaschool.medical.backend.dao.ProcedureRepository;
import com.tsystems.javaschool.medical.backend.dto.prescriptions.PrescriptionDto;
import com.tsystems.javaschool.medical.backend.dto.prescriptions.PrescriptionRequestDto;
import com.tsystems.javaschool.medical.backend.entities.DrugsEntity;
import com.tsystems.javaschool.medical.backend.entities.PatientsEntity;
import com.tsystems.javaschool.medical.backend.entities.PrescriptionsEntity;
import com.tsystems.javaschool.medical.backend.entities.ProceduresEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionCronService prescriptionCronService;
    private final PatientRepository patientRepository;
    private final ProcedureRepository procedureRepository;
    private final DrugRepository drugRepository;

    private final ModelMapper modelMapper;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, PrescriptionCronService prescriptionCronService, PatientRepository patientRepository, ProcedureRepository procedureRepository, DrugRepository drugRepository, ModelMapper modelMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionCronService = prescriptionCronService;
        this.patientRepository = patientRepository;
        this.procedureRepository = procedureRepository;
        this.drugRepository = drugRepository;
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

    public void save (PrescriptionRequestDto params){

        PrescriptionsEntity prescriptionsEntity = modelMapper.map(params, PrescriptionsEntity.class);
        PatientsEntity patientsEntity = patientRepository.getById(params.getPatientId());
        ProceduresEntity proceduresEntity = procedureRepository.getById(params.getPatientId());

        Integer drugId = params.getDrugId();
        DrugsEntity drugsEntity = drugRepository.findById(drugId).orElse(new DrugsEntity());

        prescriptionsEntity.setPatientsByPatientId(patientsEntity);
        prescriptionsEntity.setProceduresByProcedureId(proceduresEntity);
        prescriptionsEntity.setDrugsByDrugId(drugsEntity);

        prescriptionRepository.save(prescriptionsEntity);
        prescriptionCronService.generateEventsByPrescription(params);
    }

}
