package com.tsystems.javaschool.medical.services;

import com.tsystems.javaschool.medical.dto.DoctorDto;
import com.tsystems.javaschool.medical.repositories.DoctorRepository;
import com.tsystems.javaschool.medical.entities.DoctorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public List<DoctorDto> getDoctorsList() {
        List<DoctorDto> list = new LinkedList<>();
        Iterable<DoctorEntity> all = doctorRepository.findAll();
        for (DoctorEntity a : all) {
            list.add(new DoctorDto(a));
        }
        return list;
    }

    public void addDoctor(String firstName, String secondName, String lastName) {
       DoctorEntity doctorEntity = new DoctorEntity();
       doctorEntity.setFirstName(firstName);
       doctorEntity.setSecondName(secondName);
       doctorEntity.setLastName(lastName);
        doctorRepository.save(doctorEntity);
    }

    public void deleteDoctor(int id) {
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(id);
        doctorRepository.delete(doctorEntity);
    }
}
