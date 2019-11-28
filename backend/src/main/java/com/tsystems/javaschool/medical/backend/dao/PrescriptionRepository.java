package com.tsystems.javaschool.medical.backend.dao;

import com.tsystems.javaschool.medical.backend.entities.PrescriptionsEntity;
import org.springframework.data.repository.CrudRepository;

public interface PrescriptionRepository extends CrudRepository<PrescriptionsEntity, String> {
}
