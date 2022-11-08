package com.truphone.jaderbittencourt.truphoneChallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.truphone.jaderbittencourt.truphoneChallenge.dto.DeviceDto;
import com.truphone.jaderbittencourt.truphoneChallenge.dto.PatchDeviceDto;
import com.truphone.jaderbittencourt.truphoneChallenge.model.Device;
import com.truphone.jaderbittencourt.truphoneChallenge.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = DeviceController.class)
class DeviceControllerTest {

    private static final String DEVICE_ID = "636936e6554d80283ebff3b2";
    private static final String DEVICE_ID2 = "636936e8554d80283ebff3b3";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeviceService deviceService;

    @Test
    void testGetDeviceById() throws Exception {
        when(deviceService.getDeviceById(DEVICE_ID)).thenReturn(Optional.of(mockDevice()));

        MvcResult mvcResult = mockMvc.perform(get("/device/" + DEVICE_ID)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();

        mvcResult = mockMvc.perform(get("/device/" + DEVICE_ID2)
                        .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
    }

    @Test
    void testGetDeviceByBrand() throws Exception {
        when(deviceService.gedDeviceByBrand("brand")).thenReturn(Collections.singletonList(mockDevice()));

        MvcResult mvcResult = mockMvc.perform(get("/device/brand/brand")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isNotEqualTo("[]");

        mvcResult = mockMvc.perform(get("/device/brand/xpto")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testListDevices() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/device")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("[]");

        when(deviceService.listDevices()).thenReturn(Collections.singletonList(mockDevice()));
        mvcResult = mockMvc.perform(get("/device")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isNotEqualTo("[]");
    }

    @Test
    void testCreateDevice() throws Exception {
        DeviceDto deviceDto = new DeviceDto();
        mockMvc.perform(post("/device/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(deviceDto)))
                .andExpect(status().isBadRequest());

        deviceDto.setName("name");
        mockMvc.perform(post("/device/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(deviceDto)))
                .andExpect(status().isBadRequest());

        deviceDto.setName(null);
        deviceDto.setBrand("brand");
        mockMvc.perform(post("/device/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(deviceDto)))
                .andExpect(status().isBadRequest());

        deviceDto.setName("name");
        when(deviceService.createDevice(eq(deviceDto))).thenReturn(mockDevice());
        MvcResult mvcResult = mockMvc.perform(post("/device/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(deviceDto)))
                .andExpect(status().isCreated())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty();
    }

    @Test
    void testUpdateDevice() throws Exception {
        DeviceDto deviceDto = new DeviceDto();
        mockMvc.perform(put("/device/"+DEVICE_ID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(deviceDto)))
                .andExpect(status().isBadRequest());

        deviceDto.setName("name");
        mockMvc.perform(put("/device/"+DEVICE_ID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(deviceDto)))
                .andExpect(status().isBadRequest());

        deviceDto.setName(null);
        deviceDto.setBrand("brand");
        mockMvc.perform(put("/device/"+DEVICE_ID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(deviceDto)))
                .andExpect(status().isBadRequest());

        deviceDto.setName("name");
        when(deviceService.updateDevice(eq(deviceDto), eq(DEVICE_ID))).thenReturn(Optional.of(mockDevice()));
        MvcResult mvcResult = mockMvc.perform(put("/device/"+DEVICE_ID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(deviceDto)))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty();

        mockMvc.perform(put("/device/"+DEVICE_ID2)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(deviceDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testPatchDevice() throws Exception {
        PatchDeviceDto patchDeviceDto = new PatchDeviceDto();
        when(deviceService.patchDevice(eq(patchDeviceDto), eq(DEVICE_ID))).thenReturn(Optional.of(mockDevice()));
        mockMvc.perform(patch("/device/"+DEVICE_ID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patchDeviceDto)))
                .andExpect(status().isBadRequest());

        patchDeviceDto.setName(" ");
        mockMvc.perform(patch("/device/"+DEVICE_ID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patchDeviceDto)))
                .andExpect(status().isBadRequest());

        patchDeviceDto.setName(null);
        patchDeviceDto.setBrand(" ");
        mockMvc.perform(patch("/device/"+DEVICE_ID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patchDeviceDto)))
                .andExpect(status().isBadRequest());
        

        patchDeviceDto.setName("name");
        patchDeviceDto.setBrand(null);
        mockMvc.perform(patch("/device/"+DEVICE_ID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patchDeviceDto)))
                .andExpect(status().isOk());

        patchDeviceDto.setName(null);
        patchDeviceDto.setBrand("brand");
        mockMvc.perform(patch("/device/"+DEVICE_ID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patchDeviceDto)))
                .andExpect(status().isOk());

        patchDeviceDto.setName("name");
        MvcResult mvcResult = mockMvc.perform(patch("/device/"+DEVICE_ID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patchDeviceDto)))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty();

        mockMvc.perform(patch("/device/"+DEVICE_ID2)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patchDeviceDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteDevice() throws Exception {
        when(deviceService.deleteDevice(DEVICE_ID)).thenReturn(true);

        mockMvc.perform(delete("/device/"+DEVICE_ID)
                        .contentType("application/json"))
                .andExpect(status().isAccepted());
        
        mockMvc.perform(delete("/device/"+DEVICE_ID2)
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
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
