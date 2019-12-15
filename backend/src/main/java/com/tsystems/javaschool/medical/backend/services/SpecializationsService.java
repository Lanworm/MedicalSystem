package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.SpecializationRepository;
import com.tsystems.javaschool.medical.backend.dto.SpecializationsDto;
import com.tsystems.javaschool.medical.backend.entities.SpecializationsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecializationsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    SpecializationRepository specializationRepository;

    public List<SpecializationsDto> getSpecializationsList() {

        List<SpecializationsEntity> specializationsEntities = specializationRepository.getAll();
        List<SpecializationsDto> result = new ArrayList<>();

        for (Object entity : specializationsEntities) {
            SpecializationsDto specializationsDto = modelMapper.map(entity, SpecializationsDto.class);
            result.add(specializationsDto);
        }

        return result;
    }

    public void addSpecialization(String description) {
        specializationRepository.create(description);
    }

    public void deleteSpecialization(BigInteger id) {
        specializationRepository.delete(id);
    }

    public void updateSpecialization(SpecializationsDto params) {
        specializationRepository.update(params);
    }
}
