package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.DrugRepository;
import com.tsystems.javaschool.medical.backend.dto.DrugDto;
import com.tsystems.javaschool.medical.backend.entities.DrugsEntity;
import com.tsystems.javaschool.medical.backend.entities.enums.IsDeleted;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrugService {

    private final DrugRepository drugRepository;
    private final ModelMapper modelMapper;

    public DrugService(DrugRepository drugRepository, ModelMapper modelMapper) {
        this.drugRepository = drugRepository;
        this.modelMapper = modelMapper;
    }

    public List<DrugDto> getAll() {
        List<DrugsEntity> drugsEntities = (List<DrugsEntity>) drugRepository.findAll();
        List<DrugDto> drugDtos = new ArrayList<>();
        for (Object entity : drugsEntities) {
            DrugDto drugDto = modelMapper.map(entity, DrugDto.class);
            drugDtos.add(drugDto);
        }
        return drugDtos;
    }

    public void add(DrugDto params) {
        DrugsEntity drugsEntity = modelMapper.map(params, DrugsEntity.class);
        drugsEntity.setDeleted(IsDeleted.N);
        drugRepository.save(drugsEntity);
    }
}
