package com.example.communication_scheduling_api.controllers;

import com.example.communication_scheduling_api.controllers.dtos.SchedulingRequest;
import com.example.communication_scheduling_api.controllers.dtos.SchedulingResponse;
import com.example.communication_scheduling_api.business.services.SchedulingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduling")
public class SchedulingController {

    private final SchedulingService service;


    @PostMapping
    public ResponseEntity<SchedulingResponse> createScheduling(@RequestBody SchedulingRequest requestDto){
        return ResponseEntity.ok(service.createScheduling(requestDto));
    }

    @GetMapping("/{id}")
    ResponseEntity<SchedulingResponse> getSchedulingById(@PathVariable Long id){
        return ResponseEntity.ok(service.getSchedulingById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> cancelSchedulingById(@PathVariable Long id){
        service.cancelSchedulingById(id);
        return ResponseEntity.accepted().build();
    }
}
