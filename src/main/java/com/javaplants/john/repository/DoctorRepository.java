package com.javaplants.john.repository;

import com.javaplants.john.entity.DoctorEntity;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<DoctorEntity, String> {

}
