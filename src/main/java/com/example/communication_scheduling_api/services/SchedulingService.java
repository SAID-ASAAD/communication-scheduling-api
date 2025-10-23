package com.example.communication_scheduling_api.services;

import com.example.communication_scheduling_api.services.exceptions.ContactDataException;
import com.example.communication_scheduling_api.services.exceptions.ResourceNotFoundException;
import com.example.communication_scheduling_api.controllers.dtos.SchedulingRequest;
import com.example.communication_scheduling_api.controllers.dtos.SchedulingResponse;
import com.example.communication_scheduling_api.controllers.dtos.mappers.SchedulingMapper;
import com.example.communication_scheduling_api.infrastructure.repositories.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final SchedulingRepository repository;
    private final SchedulingMapper mapper;
    private final Logger log = LoggerFactory.getLogger(SchedulingService.class);

    public SchedulingResponse createScheduling(SchedulingRequest requestDto){
        log.info("Notification schedule creation process started");
        try {
            log.info("Recording and return of scheduling confirmation in process");
            return mapper.toResponseDto(repository.save(mapper.toEntity(requestDto)));
        }
        catch (DataIntegrityViolationException e) {
            log.error("Invalid format or missing data");
            throw new ContactDataException(
                    "The message, recipients email and the deliveryDateTime must be informed");
        }
    }

    public SchedulingResponse getSchedulingById(Long id){
        log.info("Scheduling finding by id in progress");
        return mapper.toResponseDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public void cancelSchedulingById(Long id){
        log.info("Scheduling cancellation by id in progress");
        repository.save(
                mapper.toEntityCancelment(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id))));
    }


}
