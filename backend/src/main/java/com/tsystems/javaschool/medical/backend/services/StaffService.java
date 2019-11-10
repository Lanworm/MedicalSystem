package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.StaffRepository;
import com.tsystems.javaschool.medical.backend.dto.StaffDto;
import com.tsystems.javaschool.medical.backend.entities.SpecializationsEntity;
import com.tsystems.javaschool.medical.backend.entities.StaffEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.tsystems.javaschool.medical.backend.util.DateUtils.getCurrentTimestamp;

@Service
public class StaffService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    StaffRepository staffRepository;

    public List<StaffDto> getStaffList() {

        List<StaffEntity> staffEntities = staffRepository.getAll();
        List<StaffDto> result = new ArrayList<>();

        for (Object item : staffEntities) {
            StaffDto staffDto = modelMapper.map(item, StaffDto.class);
            result.add(staffDto);
        }
        return result;
    }

    public void addStaff(String firstName, String secondName, String lastName, int specializationId) {
        staffRepository.create(firstName, secondName, lastName, specializationId);
    }

    public void deleteStaff(int id) {
        staffRepository.delete(id);
    }

    public void updateSpecialization(StaffDto params) {
        staffRepository.update(params);
    }
}
