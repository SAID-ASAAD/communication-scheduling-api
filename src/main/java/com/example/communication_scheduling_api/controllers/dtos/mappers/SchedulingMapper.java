package com.example.communication_scheduling_api.controllers.dtos.mappers;

import com.example.communication_scheduling_api.controllers.dtos.SchedulingRequest;
import com.example.communication_scheduling_api.controllers.dtos.SchedulingResponse;
import com.example.communication_scheduling_api.infrastructure.entities.Scheduling;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface SchedulingMapper {

    Scheduling toEntity(SchedulingRequest requestDto);
    SchedulingResponse toResponseDto(Scheduling schedulingEntity);

    @Mapping(target = "modificationDateTime", expression = "java(LocalDateTime.now())")
    @Mapping(target = "schedulingStatus", expression = "java(SchedulingStatus.CANCELED)")
    Scheduling toEntityCancelment(Scheduling scheduling);
}
