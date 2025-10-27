package com.example.communication_scheduling_api.controllers;

import com.example.communication_scheduling_api.controllers.dtos.SchedulingRequest;
import com.example.communication_scheduling_api.controllers.dtos.SchedulingResponse;
import com.example.communication_scheduling_api.infrastructure.enuns.SchedulingStatus;
import com.example.communication_scheduling_api.services.SchedulingService;
import com.example.communication_scheduling_api.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchedulingControllerTest {

    @Mock
    private SchedulingService service;

    @InjectMocks
    private SchedulingController schedulingController;

    @Test
    void createSchedulingWithSuccess(){
        LocalDateTime now = LocalDateTime.now();
        SchedulingRequest requestDto = new SchedulingRequest("message", "recipient@email", null, now.plusDays(5));
        SchedulingResponse responseDto = new SchedulingResponse(1L, "message", "recipient@email", null, now.plusDays(5), now, null, SchedulingStatus.SCHEDULED);

        when(service.createScheduling(requestDto)).thenReturn(responseDto);

        ResponseEntity<SchedulingResponse> response = schedulingController.createScheduling(requestDto);

        verify(service, times(1)).createScheduling(requestDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void getSchedulingByIdWithSuccess() {
        Long id = 1L;
        LocalDateTime now = LocalDateTime.now();
        SchedulingResponse responseDto = new SchedulingResponse(id, "message", "recipient@email", null, now.plusDays(5), now, null, SchedulingStatus.SCHEDULED);

        when(service.getSchedulingById(id)).thenReturn(responseDto);

        ResponseEntity<SchedulingResponse> response = schedulingController.getSchedulingById(id);

        verify(service, times(1)).getSchedulingById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void getSchedulingByIdWithFailure() {
        Long id = 1L;
        when(service.getSchedulingById(id)).thenThrow(new ResourceNotFoundException(id));

        assertThrows(ResourceNotFoundException.class, () -> schedulingController.getSchedulingById(id));
        verify(service, times(1)).getSchedulingById(id);
    }

    @Test
    void cancelSchedulingByIdWithSuccess() {
        Long id = 1L;
        doNothing().when(service).cancelSchedulingById(id);

        ResponseEntity<Void> response = schedulingController.cancelSchedulingById(id);

        verify(service, times(1)).cancelSchedulingById(id);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void cancelSchedulingByIdWithFailure() {
        Long id = 1L;
        doThrow(new ResourceNotFoundException(id)).when(service).cancelSchedulingById(id);

        assertThrows(ResourceNotFoundException.class, () -> schedulingController.cancelSchedulingById(id));
        verify(service, times(1)).cancelSchedulingById(id);
    }
}
