package com.truphone.jaderbittencourt.truphoneChallenge.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Data
@Document("devices")
public class Device {
    @Id
    private long id;
    private String name;
    private String brand;
    private Timestamp creationTime;
    private boolean deleted;
}
