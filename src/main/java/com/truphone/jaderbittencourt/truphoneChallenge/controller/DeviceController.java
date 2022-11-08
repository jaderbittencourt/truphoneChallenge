package com.truphone.jaderbittencourt.truphoneChallenge.controller;

import com.truphone.jaderbittencourt.truphoneChallenge.dto.DeviceDto;
import com.truphone.jaderbittencourt.truphoneChallenge.dto.PatchDeviceDto;
import com.truphone.jaderbittencourt.truphoneChallenge.model.Device;
import com.truphone.jaderbittencourt.truphoneChallenge.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable String id) {
        Optional<Device> optionalDevice = deviceService.getDeviceById(id);
        return optionalDevice
                .map(device -> new ResponseEntity<>(device, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Device>> getDeviceByBrand(@PathVariable String brand) {
        return new ResponseEntity<>(deviceService.gedDeviceByBrand(brand), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Device>> listDevices() {
        return new ResponseEntity<>(deviceService.listDevices(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Device> createDevice(@RequestBody @Valid DeviceDto deviceDto) {
        return new ResponseEntity<>(deviceService.createDevice(deviceDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable String id, @RequestBody @Valid DeviceDto deviceDto) {
        Optional<Device> optionalDevice = deviceService.updateDevice(deviceDto, id);
        return optionalDevice
                .map(device -> new ResponseEntity<>(device, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Device> patchDevice(@PathVariable String id, @RequestBody @Valid PatchDeviceDto patchDeviceDto) {
        Optional<Device> optionalDevice = deviceService.patchDevice(patchDeviceDto, id);
        return optionalDevice
                .map(device -> new ResponseEntity<>(device, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        return new ResponseEntity<>(null, deviceService.deleteDevice(id) ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND);
    }
}
