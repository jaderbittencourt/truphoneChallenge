package com.truphone.jaderbittencourt.truphoneChallenge.service;

import com.truphone.jaderbittencourt.truphoneChallenge.dto.Device;
import com.truphone.jaderbittencourt.truphoneChallenge.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    
    @Autowired
    DeviceRepository deviceRepository;

    public Optional<Device>  getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }
    public Optional<Device> searchDeviceByBrand(String brand) {
        return deviceRepository.findByBrand(brand);
    }
    public List<Device> listDevices() {
        //return deviceRepository.findAll();
        return null;
    }
    public Device createDevice(Device device) {
        return deviceRepository.save(device);
    }
    public Optional<Device> updateDevice() {
        return null;
    }
    public Optional<Device> patchDevice() {
        return null;
    }
    public Optional<Device> deleteDevice(Long id) {
        // update flag to false;
        return null;
    }
}
