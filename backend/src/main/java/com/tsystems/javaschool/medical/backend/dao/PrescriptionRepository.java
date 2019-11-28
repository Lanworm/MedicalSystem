package com.tsystems.javaschool.medical.backend.dao;

import com.tsystems.javaschool.medical.backend.entities.PrescriptionsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionRepository extends CrudRepository<PrescriptionsEntity, String> {
    @Query("from PrescriptionsEntity pe where pe.patientsByPatientId.id = :id")
    List<PrescriptionsEntity> getByUserId(@Param("id") int id);
}
