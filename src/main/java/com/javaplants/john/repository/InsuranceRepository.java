package com.javaplants.john.repository;

import com.javaplants.john.entity.InsuranceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InsuranceRepository extends CrudRepository<InsuranceEntity, String> {

}
