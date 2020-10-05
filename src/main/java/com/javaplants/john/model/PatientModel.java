package com.javaplants.john.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class PatientModel {

    private String patientId;

    private String firstName;

    private String lastName;

    private int age;

    private int visits;

    private AddressModel address;

    private InsuranceModel insurance;

    private String createdBy;

    private String modifiedBy;

    private Date createdDate;

    private Date modifiedDate;
}

