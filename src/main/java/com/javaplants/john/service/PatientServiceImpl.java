package com.javaplants.john.service;

import com.javaplants.john.entity.AddressEntity;
import com.javaplants.john.entity.DoctorEntity;
import com.javaplants.john.entity.InsuranceEntity;
import com.javaplants.john.entity.PatientEntity;
import com.javaplants.john.model.AddressModel;
import com.javaplants.john.model.InsuranceModel;
import com.javaplants.john.model.NHResponse;
import com.javaplants.john.model.PatientModel;
import com.javaplants.john.repository.AddressRepository;
import com.javaplants.john.repository.DoctorRepository;
import com.javaplants.john.repository.InsuranceRepository;
import com.javaplants.john.repository.PatientRepository;
import com.javaplants.john.utils.NHConstants;
import com.javaplants.john.utils.NHResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Override
    public NHResponse getPatientsByDoctor(String doctorId) {
        Map<String, Object> responseMap = new HashMap<>();
        List<PatientModel> patients = new ArrayList<>();
        if(!doctorId.equals("ALL")){
            patients = patientRepository.findByDoctorId(doctorId)
                    .stream()
                    .map(patient -> toBasicModel(patient))
                    .collect(Collectors.toList());

        }else{
            patients = patientRepository.findAll()
                    .stream()
                    .map(patient -> toBasicModel(patient))
                    .collect(Collectors.toList());
        }
        responseMap.put("patients", patients);

        return NHResponseGenerator.getSuccessResponse(NHConstants.SucessHttpCode,
                NHConstants.SuccessMessage,
                responseMap);
    }

    @Override
    public NHResponse getPatientById(String doctorId, String patientId) {

        Map<String, Object> responseMap = new HashMap<>();
        Optional<PatientModel> patients = patientRepository.findByPatientId(doctorId, patientId)
                .map(patient -> toModel(patient));
        responseMap.put("patients", patients.get());

        return NHResponseGenerator.getSuccessResponse(NHConstants.SucessHttpCode,
                NHConstants.SuccessMessage,
                responseMap);
    }

    @Override
    public NHResponse createPatient(PatientModel patient, String doctorId) {
        Map<String, Object> responseMap = new HashMap<>();

        InsuranceEntity insuranceEntity = insuranceRepository.save(toInsuranceEntity(patient.getInsurance(), null));
        AddressEntity addressEntity = addressRepository.save(toAddressEntity(patient.getAddress(), null));
        Optional<PatientEntity> entity = doctorRepository.findById(doctorId).map(doctor ->{
            PatientEntity patientEntity = PatientEntity.builder()
                    .firstName(patient.getFirstName())
                    .lastName(patient.getLastName())
                    .age(patient.getAge())
                    .visits(patient.getVisits())
                    .doctor(doctor)
                    .insurance(insuranceEntity)
                    .address(addressEntity)
                    .build();
             return patientRepository.save(patientEntity);

        });
        responseMap.put("patients", entity.get());
        return NHResponseGenerator.getSuccessResponse(NHConstants.SucessHttpCode,
                NHConstants.SuccessMessage,
                responseMap);
    }

    @Override
    public NHResponse updatePatient(PatientModel patient, String patientId, String doctorId) {

        Map<String, Object> responseMap = new HashMap<>();
        Optional<PatientEntity> entity = patientRepository.findByPatientId(doctorId, patientId).map(patientEntity -> {
            patientEntity.setFirstName(patient.getFirstName());
            patientEntity.setLastName(patient.getLastName());
            patientEntity.setVisits(patient.getVisits());
            patientEntity.setAge(patient.getAge());
            return patientRepository.save(patientEntity);
        });

        responseMap.put("patients", toModel(entity.get()));
        return NHResponseGenerator.getSuccessResponse(NHConstants.SucessHttpCode,
                NHConstants.SuccessMessage,
                responseMap);
    }

    private static PatientModel toBasicModel(PatientEntity patientEntity){
        return PatientModel.builder()
                .patientId(patientEntity.getPatientId())
                .firstName(patientEntity.getFirstName())
                .lastName(patientEntity.getLastName())
                .age(patientEntity.getAge())
                .visits(patientEntity.getVisits())
                .createdBy(patientEntity.getCreatedBy())
                .createdDate(patientEntity.getCreatedDate())
                .modifiedBy(patientEntity.getModifiedBy())
                .modifiedDate(patientEntity.getModifiedDate())
                .build();
    }

    private static PatientModel toModel(PatientEntity patientEntity){
        return PatientModel.builder()
                .patientId(patientEntity.getPatientId())
                .firstName(patientEntity.getFirstName())
                .lastName(patientEntity.getLastName())
                .age(patientEntity.getAge())
                .visits(patientEntity.getVisits())
                .createdBy(patientEntity.getCreatedBy())
                .createdDate(patientEntity.getCreatedDate())
                .modifiedBy(patientEntity.getModifiedBy())
                .modifiedDate(patientEntity.getModifiedDate())
                .address(toAddressModel(patientEntity.getAddress()))
                .insurance(toInsuranceModel(patientEntity.getInsurance()))
                .build();
    }

    private static PatientEntity toEntity(PatientModel patientModel, DoctorEntity doctorEntity){
        return PatientEntity.builder()
                .patientId(patientModel.getPatientId())
                .firstName(patientModel.getFirstName())
                .lastName(patientModel.getLastName())
                .age(patientModel.getAge())
                .visits(patientModel.getVisits())
                .doctor(doctorEntity)
                .build();
    }

    private static AddressModel toAddressModel(AddressEntity addressEntity){
        return AddressModel.builder()
                .addressId(addressEntity.getAddressId())
                .address1(addressEntity.getAddress1())
                .address2(addressEntity.getAddress2())
                .city(addressEntity.getCity())
                .state(addressEntity.getState())
                .zipCode(addressEntity.getZipCode())
                .createdBy(addressEntity.getCreatedBy())
                .modifiedBy(addressEntity.getModifiedBy())
                .createdDate(addressEntity.getCreatedDate())
                .modifiedDate(addressEntity.getModifiedDate())
                .build();
    }

    private static AddressEntity toAddressEntity(AddressModel addressModel, PatientEntity patient){
        return AddressEntity.builder()
                .addressId(addressModel.getAddressId())
                .address1(addressModel.getAddress1())
                .address2(addressModel.getAddress2())
                .city(addressModel.getCity())
                .state(addressModel.getState())
                .zipCode(addressModel.getZipCode())
                .patient(patient)
                .build();
    }

    private static InsuranceModel toInsuranceModel(InsuranceEntity entity){
        return InsuranceModel.builder()
                .insId(entity.getInsId())
                .insProviderId(entity.getInsProviderId())
                .insGroup(entity.getInsGroup())
                .insProvider(entity.getInsProvider())
                .insSponsor(entity.getInsSponsor())
                .createdBy(entity.getCreatedBy())
                .createdDate(entity.getCreatedDate())
                .modifiedBy(entity.getModifiedBy())
                .modifiedDate(entity.getModifiedDate())
                .build();
    }

    private static InsuranceEntity toInsuranceEntity(InsuranceModel model, PatientEntity patient){
        return InsuranceEntity.builder()
                .insId(model.getInsId())
                .insProviderId(model.getInsProviderId())
                .insGroup(model.getInsGroup())
                .insProvider(model.getInsProvider())
                .insSponsor(model.getInsSponsor())
                .patient(patient)
                .build();
    }
}
