package com.truphone.jaderbittencourt.truphoneChallenge.validator;

import com.truphone.jaderbittencourt.truphoneChallenge.dto.PatchDeviceDto;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PatchDeviceValidator implements ConstraintValidator<PatchDeviceAnnotation, PatchDeviceDto> {
    public void initialize(PatchDeviceAnnotation constraintAnnotation) {

    }

    public boolean isValid(PatchDeviceDto patchDeviceDto, ConstraintValidatorContext context) {
        if (Objects.isNull(patchDeviceDto)) {
            throw new IllegalArgumentException("@PatchDeviceAnnotation should be used only for PatchDeviceDto class and should not be null");
        }

        if (hasNoLengthOrIsEmptyString(patchDeviceDto) || hasOneAttributeWithOnlyEmptyString(patchDeviceDto)) {
            return false;
        } 
        return true;
    }
    
    private boolean hasNoLengthOrIsEmptyString(PatchDeviceDto patchDeviceDto) {
        return !hasLengthAndIsNotWhiteSpaces(patchDeviceDto.getName()) &&
                !hasLengthAndIsNotWhiteSpaces(patchDeviceDto.getBrand());
    }
    
    private boolean hasOneAttributeWithOnlyEmptyString(PatchDeviceDto patchDeviceDto) {
        return hasOnlyWhiteSpaces(patchDeviceDto.getName()) || hasOnlyWhiteSpaces(patchDeviceDto.getBrand());
    }
    
    private boolean hasLengthAndIsNotWhiteSpaces(String value) {
        return StringUtils.hasLength(value) && StringUtils.hasLength(StringUtils.trimAllWhitespace(value)); 
    }
    
    private boolean hasOnlyWhiteSpaces(String value) {
        return StringUtils.hasLength(value) && !StringUtils.hasLength(StringUtils.trimAllWhitespace(value));

    }
}
