package com.javaplants.john.controller;


import com.javaplants.john.model.NHResponse;
import com.javaplants.john.model.PatientModel;
import com.javaplants.john.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/doctor/{doctorId}/patient")
    public NHResponse getAllPatients(@PathVariable String doctorId){
        return patientService.getPatientsByDoctor(doctorId);
    }

    @GetMapping("/doctor/{doctorId}/patient/{patientId}")
    public NHResponse getPatientById(@PathVariable("doctorId") String doctorId,
                                     @PathVariable("patientId") String patientId){
        return patientService.getPatientById(doctorId, patientId);
    }

    @PostMapping("/doctor/{doctorId}/patient")
    public NHResponse createPatient(
            @RequestBody PatientModel patient,
            @PathVariable String doctorId){
        return patientService.createPatient(patient, doctorId);
    }

    @PutMapping("/doctor/{doctorId}/patient/{patientId}")
    public NHResponse updatePatient(@RequestBody PatientModel patient,
                                    @PathVariable("doctorId") String doctorId,
                                    @PathVariable("patientId") String patientId){
        return patientService.updatePatient(patient, patientId, doctorId);
    }
//
//    @DeleteMapping("/patient/{patientId}")
//    public ResponseEntity<?> deletePatient(@PathVariable("patientId") int patientId){
//        return patientService.deletePatientById(patientId);
//    }
}
