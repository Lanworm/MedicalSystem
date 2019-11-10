package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.ProcedureRepository;
import com.tsystems.javaschool.medical.backend.dto.ProceduresDto;
import com.tsystems.javaschool.medical.backend.entities.ProceduresEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProceduresService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ProcedureRepository procedureRepository;

    public List<ProceduresDto> getProceduresList() {

        List<ProceduresEntity> proceduresEntities = procedureRepository.getAll();
        List<ProceduresDto> result = new ArrayList<>();
        for (Object entity : proceduresEntities) {
            ProceduresDto proceduresDto = modelMapper.map(entity, ProceduresDto.class);
            result.add(proceduresDto);
        }
        return result;
    }

    public void addProcedure(String description) {
        procedureRepository.create(description);
    }

    public void deleteProcedure(int id) {
        procedureRepository.delete(id);
    }

    public void updateProcedure(ProceduresDto params) {
        procedureRepository.update(params);
    }
}
