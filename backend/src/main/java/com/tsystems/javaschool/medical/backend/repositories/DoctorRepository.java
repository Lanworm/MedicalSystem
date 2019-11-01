package com.tsystems.javaschool.medical.backend.repositories;

import com.tsystems.javaschool.medical.backend.entities.DoctorEntity;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<DoctorEntity, String> {

}
