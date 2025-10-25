package com.example.communication_scheduling_api.services;

import com.example.communication_scheduling_api.controllers.dtos.SchedulingRequest;
import com.example.communication_scheduling_api.controllers.dtos.SchedulingResponse;
import com.example.communication_scheduling_api.controllers.dtos.mappers.SchedulingMapper;
import com.example.communication_scheduling_api.infrastructure.entities.Scheduling;
import com.example.communication_scheduling_api.infrastructure.enuns.SchedulingStatus;
import com.example.communication_scheduling_api.infrastructure.repositories.SchedulingRepository;

import com.example.communication_scheduling_api.services.exceptions.ContactDataException;
import com.example.communication_scheduling_api.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchedulingServiceTest {

    @Mock
    private SchedulingRepository repository;
    @Mock
    private SchedulingMapper mapper;
    @InjectMocks
    private SchedulingService schedulingService;

    @Test
    void createSchedulingWithSuccess(){
        LocalDateTime now = LocalDateTime.now();
        SchedulingRequest requestDto = new SchedulingRequest("message", "recipient@email", null, now.plusDays(5));
        Scheduling entity = new Scheduling();
        SchedulingResponse responseDto = new SchedulingResponse(1L, "message", "recipient@email", null, now.plusDays(5), now, null, SchedulingStatus.SCHEDULED);

        when(mapper.toEntity(requestDto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toResponseDto(entity)).thenReturn(responseDto);

        SchedulingResponse result = schedulingService.createScheduling(requestDto);

        assertNotNull(result);
        verify(repository, times(1)).save(entity);
    }

    @Test
    void createSchedulingWithFailure() {
        SchedulingRequest requestDto = new SchedulingRequest("message", "email", null, LocalDateTime.now());
        Scheduling entity = new Scheduling();

        when(mapper.toEntity(requestDto)).thenReturn(entity);
        when(repository.save(entity)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(ContactDataException.class, () -> schedulingService.createScheduling(requestDto));
        verify(repository, times(1)).save(entity);
    }

    @Test
    void getSchedulingByIdWithSuccess(){
        Long id = 1L;
        LocalDateTime now = LocalDateTime.now();
        Scheduling entity = new Scheduling();
        SchedulingResponse responseDto = new SchedulingResponse(id, "message", "recipient@email", null, now.plusDays(5), now, null, SchedulingStatus.SCHEDULED);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toResponseDto(entity)).thenReturn(responseDto);

        SchedulingResponse result = schedulingService.getSchedulingById(id);

        assertNotNull(result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void getSchedulingByIdWithFailure() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> schedulingService.getSchedulingById(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void cancelSchedulingByIdWithSuccess() {
        Long id = 1L;
        LocalDateTime now = LocalDateTime.now();
        Scheduling foundEntity = new Scheduling(id, "message", "r@email.com", null, now, now, null, SchedulingStatus.SCHEDULED);
        Scheduling canceledEntity = new Scheduling(id, "message", "r@email.com", null, now, now, now, SchedulingStatus.CANCELED);

        when(repository.findById(id)).thenReturn(Optional.of(foundEntity));
        when(mapper.toEntityCancelment(foundEntity)).thenReturn(canceledEntity);

        ArgumentCaptor<Scheduling> schedulingCaptor = ArgumentCaptor.forClass(Scheduling.class);

        schedulingService.cancelSchedulingById(id);

        verify(repository, times(1)).save(schedulingCaptor.capture());
        Scheduling savedEntity = schedulingCaptor.getValue();

        assertEquals(SchedulingStatus.CANCELED, savedEntity.getSchedulingStatus());
        assertNotNull(savedEntity.getModificationDateTime());
    }

    @Test
    void cancelSchedulingByIdWithFailure() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> schedulingService.cancelSchedulingById(id));
        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any());
    }
}
