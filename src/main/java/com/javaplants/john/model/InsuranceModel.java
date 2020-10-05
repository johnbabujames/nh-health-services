package com.javaplants.john.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class InsuranceModel {

    private String insId;

    private String insProviderId;

    private String insProvider;

    private String insSponsor;

    private String insGroup;

    private String createdBy;

    private String modifiedBy;

    private Date createdDate;

    private Date modifiedDate;

}
