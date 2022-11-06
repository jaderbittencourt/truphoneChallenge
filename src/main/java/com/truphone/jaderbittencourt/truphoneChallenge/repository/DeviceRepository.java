package com.truphone.jaderbittencourt.truphoneChallenge.repository;

import com.truphone.jaderbittencourt.truphoneChallenge.dto.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends MongoRepository<Device, Long>{
    
    Optional<Device> findByBrand(String brand);
    
    
}
