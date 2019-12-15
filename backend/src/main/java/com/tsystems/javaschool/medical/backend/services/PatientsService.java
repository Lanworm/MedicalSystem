package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.PatientRepository;
import com.tsystems.javaschool.medical.backend.dto.PatientsDto;
import com.tsystems.javaschool.medical.backend.entities.PatientsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientsService {

    private final ModelMapper modelMapper;
    private final PatientRepository patientRepository;

    public PatientsService(ModelMapper modelMapper, PatientRepository patientRepository) {
        this.modelMapper = modelMapper;
        this.patientRepository = patientRepository;
    }

    public List<PatientsDto> getPatientsList() {

        List<PatientsDto> result = new ArrayList<>();
        List<PatientsEntity> patientsEntities = patientRepository.getAll();
        for (Object entity : patientsEntities) {
            PatientsDto patientsDto = modelMapper.map(entity, PatientsDto.class);
            result.add(patientsDto);
        }

        return result;
    }

    public PatientsDto getPatientById(BigInteger id) {
        return modelMapper.map(patientRepository.getById(id), PatientsDto.class);
    }

    public void addPatient(String firstName, String secondName, String lastName, String insuranceNumber) {
        patientRepository.create(firstName, secondName, lastName, insuranceNumber);
    }

    public void deletePatient(BigInteger id) {
        patientRepository.delete(id);
    }

    public void updatePatient(PatientsDto params) {
        patientRepository.update(params);
    }
}
