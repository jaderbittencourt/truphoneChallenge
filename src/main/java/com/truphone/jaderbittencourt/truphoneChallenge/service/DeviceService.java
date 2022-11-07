package com.truphone.jaderbittencourt.truphoneChallenge.service;

import com.truphone.jaderbittencourt.truphoneChallenge.dto.DeviceDto;
import com.truphone.jaderbittencourt.truphoneChallenge.dto.PatchDeviceDto;
import com.truphone.jaderbittencourt.truphoneChallenge.model.Device;
import com.truphone.jaderbittencourt.truphoneChallenge.repository.DeviceRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    ModelMapper mapper;    

    public Optional<Device> getDeviceById(String id) {
        return deviceRepository.findActiveDeviceById(new ObjectId(id));
    }
    
    public List<Device> gedDeviceByBrand(String brand) {
        return deviceRepository.findActiveDeviceByBrand(brand);
    }
    
    public List<Device> listDevices() {
        return deviceRepository.findActiveDevices();
    }
    
    public Device createDevice(DeviceDto deviceDto) {
        Device device = mapper.map(deviceDto, Device.class);
        device.setCreationTime(LocalDateTime.now());
        return deviceRepository.save(device);
    }
    
    public Optional<Device> updateDevice(DeviceDto deviceDto, String id) {
        Optional<Device> optionalDevice = deviceRepository.findActiveDeviceById(new ObjectId(id));
        if (optionalDevice.isEmpty()) {
            return optionalDevice;
        }
        
        Device device = optionalDevice.get();
        mapper.map(deviceDto, device);
        return Optional.of(deviceRepository.save(device));
    }
    
    public Optional<Device> patchDevice(PatchDeviceDto patchDeviceDto, String id) {
        Optional<Device> optionalDevice = deviceRepository.findActiveDeviceById(new ObjectId(id));
        if (optionalDevice.isEmpty()) {
            return optionalDevice;
        }

        Device device = optionalDevice.get();
        mapper.map(patchDeviceDto, device);
        return Optional.of(deviceRepository.save(device));
    }
    
    public boolean deleteDevice(String id) {
        Optional<Device> optionalDevice = deviceRepository.findActiveDeviceById(new ObjectId(id));
        if (optionalDevice.isEmpty()) {
            return false;
        }
        Device device = optionalDevice.get();
        device.setDeleted(true);
        deviceRepository.save(device);
        return true;
    }
}
