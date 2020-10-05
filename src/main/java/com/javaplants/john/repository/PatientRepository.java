package com.javaplants.john.repository;

import com.javaplants.john.entity.PatientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends CrudRepository<PatientEntity, String> {

    @Query(value = "select p from PatientEntity p")
    List<PatientEntity> findAll();

    @Query(value = "select p from PatientEntity p where p.doctor.doctorId = :doctorId")
    List<PatientEntity> findByDoctorId(@Param("doctorId") String doctorId);

    @Query(value = "select p from PatientEntity p where p.doctor.doctorId = :doctorId and p.patientId = :patientId")
    Optional<PatientEntity> findByPatientId(@Param("doctorId") String doctorId, @Param("patientId") String patientId);

}
