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
            DoctorDto doctorDto = new DoctorDto();
            doctorDto.setId(a.getId());
            doctorDto.setFirstName(a.getFirstName());
            doctorDto.setSecondName(a.getSecondName());
            doctorDto.setLastName(a.getLastName());
            list.add(doctorDto);
        }
        return list;
    }
}
