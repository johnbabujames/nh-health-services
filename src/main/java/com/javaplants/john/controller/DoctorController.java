package com.javaplants.john.controller;

import com.javaplants.john.model.DoctorModel;
import com.javaplants.john.model.NHResponse;
import com.javaplants.john.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctor")
    public NHResponse getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctor/{doctorId}")
    public NHResponse getDoctorById(@PathVariable("doctorId") String doctorId) {
        return doctorService.getDoctor(doctorId);
    }

    @PostMapping("/doctor")
    public NHResponse createDoctor(@RequestBody DoctorModel doctor) {
        return doctorService.createDoctor(doctor);
    }

    @PutMapping("/doctor/{doctorId}")
    public NHResponse updateDoctor(@PathVariable("doctorId") String doctorId, @RequestBody DoctorModel doctor) {
        return doctorService.updateDoctor(doctorId, doctor);
    }

    @DeleteMapping("/doctor/{doctorId}")
    public NHResponse deleteDoctor(@PathVariable("doctorId") String doctorId) {
        return doctorService.deleteDoctor(doctorId);
    }

}