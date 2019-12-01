package com.tsystems.javaschool.medical.backend.dao;

import com.tsystems.javaschool.medical.backend.entities.DrugsEntity;
import org.springframework.data.repository.CrudRepository;

public interface DrugRepository extends CrudRepository<DrugsEntity, Integer> {

}
