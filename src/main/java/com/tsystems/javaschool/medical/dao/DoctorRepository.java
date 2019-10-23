package com.tsystems.javaschool.medical.dao;

import com.tsystems.javaschool.medical.models.DoctorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity, String> {

    DoctorEntity findById(int id);

    List<DoctorEntity> findAll();

}
