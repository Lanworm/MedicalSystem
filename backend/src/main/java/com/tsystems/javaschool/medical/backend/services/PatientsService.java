package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.PatientRepository;
import com.tsystems.javaschool.medical.backend.dto.PatientsDto;
import com.tsystems.javaschool.medical.backend.entities.PatientsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    PatientRepository patientRepository;

    public List<PatientsDto> getPatientsList() {

        List<PatientsDto> result = new ArrayList<>();
        List<PatientsEntity> patientsEntities = patientRepository.getAll();
        for (Object entity : patientsEntities) {
            PatientsDto patientsDto = modelMapper.map(entity, PatientsDto.class);
            result.add(patientsDto);
        }

        return result;
    }

    public void addPatient(String firstName, String secondName, String lastName, String insuranceNumber) {
        patientRepository.create(firstName, secondName, lastName, insuranceNumber);
    }

    public void deletePatient(int id) {
        patientRepository.delete(id);
    }

    public void updatePatient(PatientsDto params) {
        patientRepository.update(params);
    }
}
