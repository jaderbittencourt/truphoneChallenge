package com.truphone.jaderbittencourt.truphoneChallenge.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DeviceDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String brand;
    
}
