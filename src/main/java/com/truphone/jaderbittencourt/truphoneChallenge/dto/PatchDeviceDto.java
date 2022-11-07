package com.truphone.jaderbittencourt.truphoneChallenge.dto;

import com.truphone.jaderbittencourt.truphoneChallenge.validator.PatchDeviceAnnotation;
import lombok.Data;

@Data
@PatchDeviceAnnotation
public class PatchDeviceDto {
    
    private String name;
    private String brand;
}
