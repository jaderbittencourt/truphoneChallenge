package com.truphone.jaderbittencourt.truphoneChallenge.validator;

import com.truphone.jaderbittencourt.truphoneChallenge.dto.PatchDeviceDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatchDeviceValidatorTest {

    PatchDeviceValidator validator = new PatchDeviceValidator();
    
    @Test
    public void testNullObject() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.isValid(null, null);
        });
        assertNotNull(exception);
    }

    @Test
    public void testIsValid()  {
        PatchDeviceDto patchDeviceDto = new PatchDeviceDto();

        patchDeviceDto.setName("x");
        patchDeviceDto.setBrand("y");
        assertTrue(validator.isValid(patchDeviceDto, null));

        patchDeviceDto.setName(null);
        patchDeviceDto.setBrand("y");
        assertTrue(validator.isValid(patchDeviceDto, null));

        patchDeviceDto.setName("x");
        patchDeviceDto.setBrand(null);
        assertTrue(validator.isValid(patchDeviceDto, null));

        patchDeviceDto.setName("x");
        patchDeviceDto.setBrand(" ");
        assertFalse(validator.isValid(patchDeviceDto, null));

        patchDeviceDto.setName(" ");
        patchDeviceDto.setBrand("y");
        assertFalse(validator.isValid(patchDeviceDto, null));

        patchDeviceDto.setName(" ");
        patchDeviceDto.setBrand(" ");
        assertFalse(validator.isValid(patchDeviceDto, null));

        patchDeviceDto.setName("");
        patchDeviceDto.setBrand("");
        assertFalse(validator.isValid(patchDeviceDto, null));

        patchDeviceDto.setName(null);
        patchDeviceDto.setBrand(null);
        assertFalse(validator.isValid(patchDeviceDto, null));

    }
}
