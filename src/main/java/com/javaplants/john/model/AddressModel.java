package com.javaplants.john.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class AddressModel {

    private String addressId;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private int zipCode;

    private String createdBy;

    private String modifiedBy;

    private Date createdDate;

    private Date modifiedDate;
}
