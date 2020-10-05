package com.javaplants.john.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class DoctorModel{

    private String doctorId;

    private String firstName;

    private String lastName;

    private int rating;

    private int age;

    private int experience;

    private String createdBy;

    private String modifiedBy;

    private Date createdDate;

    private Date modifiedDate;
}

