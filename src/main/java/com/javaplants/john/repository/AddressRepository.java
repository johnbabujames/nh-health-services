package com.javaplants.john.repository;

import com.javaplants.john.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

}
