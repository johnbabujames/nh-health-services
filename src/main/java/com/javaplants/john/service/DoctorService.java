package com.javaplants.john.service;


import com.javaplants.john.model.DoctorModel;
import com.javaplants.john.model.NHResponse;

public interface DoctorService {

    public NHResponse getAllDoctors();

    public NHResponse getDoctor(String doctorId);

    public NHResponse createDoctor(DoctorModel doctor);

    public NHResponse updateDoctor(String doctorId, DoctorModel doctor);

    public NHResponse deleteDoctor(String doctorId);

}
