package com.truphone.jaderbittencourt.truphoneChallenge.controller;

import com.truphone.jaderbittencourt.truphoneChallenge.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping("/{id}")
    public ResponseEntity<Void> getDeviceById(@PathVariable Long id) {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<Void> searchDeviceByBrand(@PathVariable String brand) {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("")
    public ResponseEntity<Void> listDevices() {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createDevice() {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDevice(@PathVariable Long id) {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchDevice(@PathVariable Long id) {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }


}
