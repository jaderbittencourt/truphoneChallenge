package com.truphone.jaderbittencourt.truphoneChallenge.service;

import com.truphone.jaderbittencourt.truphoneChallenge.dto.DeviceDto;
import com.truphone.jaderbittencourt.truphoneChallenge.dto.PatchDeviceDto;
import com.truphone.jaderbittencourt.truphoneChallenge.mapper.PatchDeviceDtoToDeviceConverter;
import com.truphone.jaderbittencourt.truphoneChallenge.model.Device;
import com.truphone.jaderbittencourt.truphoneChallenge.repository.DeviceRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class DeviceServiceTest {
    
    @Mock
    DeviceRepository deviceRepository;
    @InjectMocks
    DeviceService deviceService;
    
    private static final String DEVICE_ID = "636936e6554d80283ebff3b2";
    private static final String DEVICE_ID2 = "636936e8554d80283ebff3b3";
    private static final String BRAND = "brand";
    
    @BeforeEach
    public void setUp() {
        deviceService.mapper = modelMapper();
        deviceService.deviceRepository = deviceRepository;
    }

    @Test
    public void testGetDeviceById() {
        Mockito.when(deviceRepository.findActiveDeviceById(eq(new ObjectId(DEVICE_ID))))
                .thenReturn(Optional.of(mockDevice()));

        Device device = deviceService.getDeviceById(DEVICE_ID).get();
        assertNotNull(device);
        assertTrue(deviceService.getDeviceById(DEVICE_ID2).isEmpty());
    }
    
    @Test
    public void testGedDeviceByBrand() {
        Mockito.when(deviceRepository.findActiveDeviceByBrand(eq(BRAND)))
                .thenReturn(Collections.singletonList(mockDevice()));

        List<Device> devices = deviceService.gedDeviceByBrand(BRAND);
        assertNotNull(devices);
        assertFalse(devices.isEmpty());

        devices = deviceService.gedDeviceByBrand("garlic");
        assertTrue(devices.isEmpty());
    }
    
    @Test
    public void testListDevices() {
        Mockito.when(deviceRepository.findActiveDevices())
                .thenReturn(Collections.singletonList(mockDevice()));

        List<Device> devices = deviceService.listDevices();
        assertNotNull(devices);
        assertFalse(devices.isEmpty());

        Mockito.when(deviceRepository.findActiveDevices())
                .thenReturn(Collections.emptyList());

        devices = deviceService.gedDeviceByBrand("garlic");
        assertTrue(devices.isEmpty());
    }
    
    @Test
    public void testCreateDevice() {
        DeviceDto deviceDto = newDeviceDto();
        
        Mockito.when(deviceRepository.save(any()))
                .thenReturn(mockDevice(deviceDto.getName(), deviceDto.getBrand()));
        
        Device device = deviceService.createDevice(deviceDto);
        assertNotNull(device);
        assertEquals(device.getName(), deviceDto.getName());
        assertEquals(device.getBrand(), deviceDto.getBrand());
        assertNotNull(device.get_id());
        assertNotNull(device.getCreationTime());
    }

    @Test
    public void testUpdateDevice() {
        DeviceDto deviceDto = newDeviceDto();

        Mockito.when(deviceRepository.findActiveDeviceById(eq(new ObjectId(DEVICE_ID))))
                .thenReturn(Optional.of(mockDevice("oldName", "oldBrand")));
        Mockito.when(deviceRepository.save(any()))
                .thenReturn(mockDevice(deviceDto.getName(), deviceDto.getBrand()));
                
        Device device = deviceService.updateDevice(deviceDto, DEVICE_ID).get();
        assertNotNull(device);
        assertEquals(device.getName(), deviceDto.getName());
        assertEquals(device.getBrand(), deviceDto.getBrand());
        assertNotNull(device.get_id());
        assertNotNull(device.getCreationTime());
    }

    @Test
    public void testPatchDeviceName() {
        PatchDeviceDto patchDeviceDto = new PatchDeviceDto();
        patchDeviceDto.setName("garlic");
        
        Mockito.when(deviceRepository.findActiveDeviceById(eq(new ObjectId(DEVICE_ID))))
                .thenReturn(Optional.of(mockDevice("oldName", "oldBrand")));
        Mockito.when(deviceRepository.save(any()))
                .thenReturn(mockDevice(patchDeviceDto.getName(), "oldBrand"));

        Device device = deviceService.patchDevice(patchDeviceDto, DEVICE_ID).get();
        assertNotNull(device);
        assertEquals(device.getName(), patchDeviceDto.getName());
        assertNotEquals(device.getBrand(), patchDeviceDto.getBrand());
        assertNotNull(device.get_id());
        assertNotNull(device.getCreationTime());
    }

    @Test
    public void testPatchDeviceBrand() {
        PatchDeviceDto patchDeviceDto = new PatchDeviceDto();
        patchDeviceDto.setBrand("garlic");

        Mockito.when(deviceRepository.findActiveDeviceById(eq(new ObjectId(DEVICE_ID))))
                .thenReturn(Optional.of(mockDevice("oldName", "oldBrand")));
        Mockito.when(deviceRepository.save(any()))
                .thenReturn(mockDevice("oldName", patchDeviceDto.getBrand()));

        Device device = deviceService.patchDevice(patchDeviceDto, DEVICE_ID).get();
        assertNotNull(device);
        assertNotEquals(device.getName(), patchDeviceDto.getName());
        assertEquals(device.getBrand(), patchDeviceDto.getBrand());
        assertNotNull(device.get_id());
        assertNotNull(device.getCreationTime());
    }

    @Test
    public void testDeleteDevice() {
        Mockito.when(deviceRepository.findActiveDeviceById(eq(new ObjectId(DEVICE_ID))))
                .thenReturn(Optional.of(mockDevice()));
        assertTrue(deviceService.deleteDevice(DEVICE_ID));

        Mockito.when(deviceRepository.findActiveDeviceById(eq(new ObjectId(DEVICE_ID2))))
                .thenReturn(Optional.empty());
        assertFalse(deviceService.deleteDevice(DEVICE_ID2));
    }
    
    private ModelMapper modelMapper() {
        PatchDeviceDtoToDeviceConverter patchDeviceDtoToDeviceConverter = new PatchDeviceDtoToDeviceConverter();
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.createTypeMap(PatchDeviceDto.class, Device.class).setConverter(patchDeviceDtoToDeviceConverter);
        return modelMapper;
    }
    
    private DeviceDto newDeviceDto() {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setName("name");
        deviceDto.setBrand("brand");
        return deviceDto;
    }

    private Device mockDevice() {
        return mockDevice("name", "brand");
    }
    
    private Device mockDevice(String name, String brand) {
        Device device = new Device();
        device.setName(name);
        device.setBrand(brand);
        device.set_id(DEVICE_ID);
        device.setCreationTime(LocalDateTime.now());
        return device;
    }
}
