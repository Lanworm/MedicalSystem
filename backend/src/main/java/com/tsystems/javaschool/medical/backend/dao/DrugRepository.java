package com.tsystems.javaschool.medical.backend.dao;

import com.tsystems.javaschool.medical.backend.entities.DrugsEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface DrugRepository extends CrudRepository<DrugsEntity, BigInteger> {

}
