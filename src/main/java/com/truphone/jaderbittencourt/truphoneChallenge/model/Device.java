package com.truphone.jaderbittencourt.truphoneChallenge.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("devices")
public class Device {
    
    private String _id;
    private String name;
    private String brand;
    private LocalDateTime creationTime;
    private boolean deleted;
}
