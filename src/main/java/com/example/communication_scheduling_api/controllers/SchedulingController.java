package com.example.communication_scheduling_api.controllers;

import com.example.communication_scheduling_api.business.dtos.SchedulingRequest;
import com.example.communication_scheduling_api.business.dtos.SchedulingResponse;
import com.example.communication_scheduling_api.business.mappers.SchedulingMapper;
import com.example.communication_scheduling_api.business.services.SchedulingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduling")
public class SchedulingController {

    private final SchedulingService service;
    private final SchedulingMapper mapper;

    @PostMapping
    public ResponseEntity<SchedulingResponse> createScheduling(@RequestBody SchedulingRequest requestDto){
        return ResponseEntity.ok(service.createScheduling(requestDto));
    }

    @GetMapping("/{id}")
    ResponseEntity<SchedulingResponse> getSchedulingById(@PathVariable Long id){
        return ResponseEntity.ok(service.getSchedulingById(id));
    }
}
