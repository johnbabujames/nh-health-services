package com.javaplants.john.entity;

import com.javaplants.john.utils.StringPrefixedSequenceIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient")
@Builder(toBuilder = true)
public class PatientEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
    @GenericGenerator(
            name = "patient_seq",
            strategy = "com.javaplants.john.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PAT_"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "visits")
    private int visits;

    @Column(name = "age")
    private int age;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "doctor_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_doctor_id"
            )
    )
    private DoctorEntity doctor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_pat_add_id"
            ),
            referencedColumnName = "address_id")
    private AddressEntity address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ins_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_pat_ins_id"
            ),
            referencedColumnName = "ins_id")
    private InsuranceEntity insurance;

}