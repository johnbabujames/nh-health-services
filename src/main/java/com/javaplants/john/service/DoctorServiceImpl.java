package com.javaplants.john.service;

import com.javaplants.john.entity.DoctorEntity;
import com.javaplants.john.model.DoctorModel;
import com.javaplants.john.model.NHResponse;
import com.javaplants.john.repository.DoctorRepository;
import com.javaplants.john.utils.NHConstants;
import com.javaplants.john.utils.NHResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    @Override
    public NHResponse getAllDoctors() {
        Map<String, Object> responseMap = new HashMap<>();
        List<DoctorModel> doctors = new ArrayList<>();
        doctorRepository.findAll().forEach(doctorEntity -> {
            doctors.add(toModel(doctorEntity));
        });
        responseMap.put("doctors", doctors);
        return NHResponseGenerator.getSuccessResponse(NHConstants.SucessHttpCode,
                NHConstants.SuccessMessage,
                responseMap);
    }

    @Override
    public NHResponse getDoctor(String doctorId) {
        return doctorRepository.findById(doctorId)
                .map(doctorEntity -> {
                    Map<String, Object> responseMap = new HashMap<>();
                    responseMap.put("doctors", toModel(doctorEntity));
                    return NHResponseGenerator.getSuccessResponse(NHConstants.SucessHttpCode,
                            NHConstants.SuccessMessage,
                            responseMap);
                }).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public NHResponse createDoctor(DoctorModel doctor) {

        return Optional.of(toEntity(doctor))
                .map(doctorRepository::save)
                .map(doctorEntity -> {
                    Map<String, Object> responseMap = new HashMap<>();
                    responseMap.put("doctors", toModel(doctorEntity));
                    return NHResponseGenerator.getSuccessResponse(NHConstants.SucessHttpCode,
                            NHConstants.SuccessMessage,
                            responseMap);
                }).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public NHResponse updateDoctor(String doctorId, DoctorModel doctor) {

         return doctorRepository.findById(doctorId)
                .map(patientEntity -> toEntity(doctor))
                .map(doctorRepository::save)
                .map(doctorEntity -> {
                    Map<String, Object> responseMap = new HashMap<>();
                    responseMap.put("doctors", toModel(doctorEntity));
                    return NHResponseGenerator.getSuccessResponse(NHConstants.SucessHttpCode,
                            NHConstants.SuccessMessage,
                            responseMap);
                }).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public NHResponse deleteDoctor(String doctorId) {
        return doctorRepository.findById(doctorId)
                .map(doctorEntity -> {
                    doctorRepository.delete(doctorEntity);
                    Map<String, Object> responseMap = new HashMap<>();
                    responseMap.put("doctors", toModel(doctorEntity));
                    return NHResponseGenerator.getSuccessResponse(NHConstants.SucessHttpCode,
                            NHConstants.SuccessMessage,
                            responseMap);
                }).orElseThrow(IllegalAccessError::new);
    }

    private static DoctorModel toModel(DoctorEntity doctorEntity) {
        return DoctorModel.builder()
                .doctorId(doctorEntity.getDoctorId())
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .age(doctorEntity.getAge())
                .experience(doctorEntity.getExperience())
                .rating(doctorEntity.getRating())
                .createdBy(doctorEntity.getCreatedBy())
                .createdDate(doctorEntity.getCreatedDate())
                .modifiedBy(doctorEntity.getModifiedBy())
                .modifiedDate(doctorEntity.getModifiedDate())
                .build();
    }

    private static DoctorEntity toEntity(DoctorModel doctorModel) {
        return DoctorEntity.builder()
                .doctorId(doctorModel.getDoctorId())
                .firstName(doctorModel.getFirstName())
                .lastName(doctorModel.getLastName())
                .age(doctorModel.getAge())
                .experience(doctorModel.getExperience())
                .rating(doctorModel.getRating())
                .build();
    }
}
