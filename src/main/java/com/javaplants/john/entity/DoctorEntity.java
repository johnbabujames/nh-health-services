package com.javaplants.john.entity;

import com.javaplants.john.utils.StringPrefixedSequenceIdGenerator;
import com.sun.corba.se.spi.ior.Identifiable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "doctor")
@Builder(toBuilder = true)
public class DoctorEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    @GenericGenerator(
            name = "doctor_seq",
            strategy = "com.javaplants.john.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "DOC_"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    @Column(name="doctor_id")
    private String doctorId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="rating")
    private int rating;

    @Column(name="age")
    private int age;

    @Column(name="experience")
    private int experience;

}