package com.example.communication_scheduling_api.business.services;

import com.example.communication_scheduling_api.business.services.exceptions.ContactDataException;
import com.example.communication_scheduling_api.business.services.exceptions.ResourceNotFoundException;
import com.example.communication_scheduling_api.controllers.dtos.SchedulingRequest;
import com.example.communication_scheduling_api.controllers.dtos.SchedulingResponse;
import com.example.communication_scheduling_api.controllers.dtos.mappers.SchedulingMapper;
import com.example.communication_scheduling_api.infrastructure.repositories.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final SchedulingRepository repository;
    private final SchedulingMapper mapper;

    public SchedulingResponse createScheduling(SchedulingRequest requestDto){
        try {
            return mapper.toResponseDto(repository.save(mapper.toEntity(requestDto)));
        }
        catch (DataIntegrityViolationException e) {
            throw new ContactDataException(
                    "The message, recipients email and the deliveryDateTime must be informed");
        }
    }

    public SchedulingResponse getSchedulingById(Long id){
        return mapper.toResponseDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public void cancelSchedulingById(Long id){
        repository.save(
                mapper.toEntityCancelment(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id))));
    }


}
