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
@Table(name = "insurance")
@Builder(toBuilder = true)
public class InsuranceEntity extends AuditEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "insurance_seq")
    @GenericGenerator(
            name = "insurance_seq",
            strategy = "com.javaplants.john.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "INS_"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    @Column(name = "ins_id")
    private String insId;

    @Column(name = "ins_provider_id")
    private String insProviderId;

    @Column(name = "ins_provider")
    private String insProvider;

    @Column(name = "ins_sponsor")
    private String insSponsor;

    @Column(name = "ins_group")
    private String insGroup;

    @OneToOne(mappedBy = "address")
    private PatientEntity patient;

}