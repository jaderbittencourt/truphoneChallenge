package com.truphone.jaderbittencourt.truphoneChallenge.repository;

import com.truphone.jaderbittencourt.truphoneChallenge.model.Device;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends MongoRepository<Device, ObjectId>{

    
    @Query("{ '_id' : ?0, 'deleted': false}")
    Optional<Device> findActiveDeviceById(ObjectId objectId);

    @Query("{ 'brand' : ?0, 'deleted': false}")
    List<Device> findActiveDeviceByBrand(String brand);

    @Query("{'deleted': false}")
    List<Device> findActiveDevices();
    
    
}
