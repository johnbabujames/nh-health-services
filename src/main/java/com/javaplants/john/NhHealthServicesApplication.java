package com.javaplants.john;

import com.javaplants.john.entity.AddressEntity;
import com.javaplants.john.entity.DoctorEntity;
import com.javaplants.john.entity.InsuranceEntity;
import com.javaplants.john.entity.PatientEntity;
import com.javaplants.john.repository.AddressRepository;
import com.javaplants.john.repository.DoctorRepository;
import com.javaplants.john.repository.InsuranceRepository;
import com.javaplants.john.repository.PatientRepository;
import com.javaplants.john.service.AuditorAwareImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;
import java.util.List;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class NhHealthServicesApplication {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    public static void main(String[] args) {
        SpringApplication.run(NhHealthServicesApplication.class, args);
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            DoctorEntity doctor1 = DoctorEntity.builder()
                    .firstName("Gregory")
                    .lastName("Lam")
                    .age(45)
                    .experience(10)
                    .rating(5)
                    .build();

            DoctorEntity doctor2 = DoctorEntity.builder()
                    .firstName("Aeron")
                    .lastName("Shaftel")
                    .age(51)
                    .experience(16)
                    .rating(4)
                    .build();

            DoctorEntity doctor3 = DoctorEntity.builder()
                    .firstName("Connie")
                    .lastName("Gowrich")
                    .age(55)
                    .experience(20)
                    .rating(5)
                    .build();

            DoctorEntity doctor4 = DoctorEntity.builder()
                    .firstName("Andrew")
                    .lastName("Jofrdon")
                    .age(43)
                    .experience(8)
                    .rating(3)
                    .build();

            DoctorEntity doctor5 = DoctorEntity.builder()
                    .firstName("Antony")
                    .lastName("Phillips")
                    .age(50)
                    .experience(12)
                    .rating(4)
                    .build();

            List<DoctorEntity> doctors = Arrays.asList(doctor1, doctor2, doctor3, doctor4, doctor5);

            doctors.forEach(doctor -> {
                doctorRepository.save(doctor);
            });

//            doctorRepository.findById(10001).map(doctor ->{
//                PatientEntity patient = PatientEntity.builder()
//                        .firstName("John")
//                        .lastName("James")
//                        .age(36)
//                        .visits(0)
//                        .doctor(doctor)
//                        .build();
//                return patientRepository.save(patient);
//            });
//
//            doctorRepository.findById(10002).map(doctor ->{
//                PatientEntity patient = PatientEntity.builder()
//                        .firstName("Sadeesh")
//                        .lastName("Ramakrishnan")
//                        .age(40)
//                        .visits(0)
//                        .doctor(doctor)
//                        .build();
//                return patientRepository.save(patient);
//            });
//
//            doctorRepository.findById(10003).map(doctor ->{
//                PatientEntity patient = PatientEntity.builder()
//                        .firstName("Ram")
//                        .lastName("Subramanian")
//                        .age(33)
//                        .visits(0)
//                        .doctor(doctor)
//                        .build();
//                return patientRepository.save(patient);
//            });
//
//            doctorRepository.findById(10004).map(doctor ->{
//                PatientEntity patient = PatientEntity.builder()
//                        .firstName("Vimal")
//                        .lastName("Prasad")
//                        .age(38)
//                        .visits(0)
//                        .doctor(doctor)
//                        .build();
//                return patientRepository.save(patient);
//            });
//
//            patientRepository.findById(20001).map(patient -> {
//                AddressEntity address = AddressEntity.builder()
//                        .address1("1136 Quail Roost")
//                        .address2("1136")
//                        .city("Pittsburgh")
//                        .state("PA")
//                        .zipCode(15237)
//                        .patient(patient)
//                        .build();
//                addressRepository.save(address);
//                InsuranceEntity insurance = InsuranceEntity.builder()
//                        .insId("AAAAA")
//                        .insGroup("group1")
//                        .insProvider("uhc")
//                        .insSponsor("cgi")
//                        .patient(patient)
//                        .build();
//                insuranceRepository.save(insurance);
//                return null;
//            });
//
//        };

        };
    }

}
