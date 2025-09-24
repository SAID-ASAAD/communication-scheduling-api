package com.example.communication_scheduling_api.business.mappers;

import com.example.communication_scheduling_api.business.dtos.SchedulingRequest;
import com.example.communication_scheduling_api.business.dtos.SchedulingResponse;
import com.example.communication_scheduling_api.infrastructure.entities.Scheduling;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface SchedulingMapper {

    Scheduling toEntity(SchedulingRequest requestDto);
    SchedulingResponse toResponse(Scheduling schedulingEntity);
}
