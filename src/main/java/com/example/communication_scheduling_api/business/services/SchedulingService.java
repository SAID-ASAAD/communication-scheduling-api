package com.example.communication_scheduling_api.business.services;

import com.example.communication_scheduling_api.business.dtos.SchedulingRequest;
import com.example.communication_scheduling_api.business.dtos.SchedulingResponse;
import com.example.communication_scheduling_api.business.mappers.SchedulingMapper;
import com.example.communication_scheduling_api.infrastructure.repositories.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final SchedulingRepository repository;
    private final SchedulingMapper mapper;

    public SchedulingResponse createScheduling(SchedulingRequest requestDto){
        return mapper.toResponseDto(repository.save(mapper.toEntity(requestDto)));
    }

    public SchedulingResponse getSchedulingById(Long id){
        return mapper.toResponseDto(repository.findById(id).orElseThrow());
    }


}
