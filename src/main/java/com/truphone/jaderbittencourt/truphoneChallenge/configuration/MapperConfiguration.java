package com.truphone.jaderbittencourt.truphoneChallenge.configuration;

import com.truphone.jaderbittencourt.truphoneChallenge.dto.PatchDeviceDto;
import com.truphone.jaderbittencourt.truphoneChallenge.mapper.PatchDeviceDtoToDeviceConverter;
import com.truphone.jaderbittencourt.truphoneChallenge.model.Device;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    private final PatchDeviceDtoToDeviceConverter patchDeviceDtoToDeviceConverter;

    public MapperConfiguration(PatchDeviceDtoToDeviceConverter patchDeviceDtoToDeviceConverter) {
        this.patchDeviceDtoToDeviceConverter = patchDeviceDtoToDeviceConverter;
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.createTypeMap(PatchDeviceDto.class, Device.class).setConverter(this.patchDeviceDtoToDeviceConverter);
        return modelMapper;
    }
}
