package com.tsystems.javaschool.medical.repositories;

import com.tsystems.javaschool.medical.entities.DoctorEntity;
import org.springframework.data.repository.CrudRepository;


public interface DoctorRepository extends CrudRepository<DoctorEntity, String> {

}
