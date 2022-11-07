package com.truphone.jaderbittencourt.truphoneChallenge.mapper;

import com.truphone.jaderbittencourt.truphoneChallenge.dto.PatchDeviceDto;
import com.truphone.jaderbittencourt.truphoneChallenge.model.Device;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class PatchDeviceDtoToDeviceConverter implements Converter<PatchDeviceDto, Device> {

    @Override
    public Device convert(MappingContext<PatchDeviceDto, Device> mappingContext) {
        final PatchDeviceDto patchDeviceDto = mappingContext.getSource();
        Device device = Objects.nonNull(mappingContext.getDestination()) ? mappingContext.getDestination() : new Device();
        
        if (StringUtils.hasLength(patchDeviceDto.getName())) {
            device.setName(patchDeviceDto.getName());
        }
        if (StringUtils.hasLength(patchDeviceDto.getBrand())) {
            device.setBrand(patchDeviceDto.getBrand());
        }
        return device;
    }
}
