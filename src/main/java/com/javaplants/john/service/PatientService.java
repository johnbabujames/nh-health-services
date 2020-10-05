package com.javaplants.john.service;

import com.javaplants.john.model.NHResponse;
import com.javaplants.john.model.PatientModel;

public interface PatientService {

    public NHResponse getPatientsByDoctor(String doctorId);

    public NHResponse getPatientById(String doctorId, String patientId);

    public NHResponse createPatient(PatientModel patient, String doctorId);

    public NHResponse updatePatient(PatientModel patient, String patientId, String doctorId);

}
