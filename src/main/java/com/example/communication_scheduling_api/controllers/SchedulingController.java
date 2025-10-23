package com.example.communication_scheduling_api.controllers;

import com.example.communication_scheduling_api.controllers.dtos.SchedulingRequest;
import com.example.communication_scheduling_api.controllers.dtos.SchedulingResponse;
import com.example.communication_scheduling_api.services.SchedulingService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduling")
public class SchedulingController {

    private final SchedulingService service;


    @PostMapping
    @Operation(description = "Endpoint responsible for creating a notification schedule and saving it to the database")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Notification scheduled successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Json: missing mandatory data or wrong formatting")
    })
    public ResponseEntity<SchedulingResponse> createScheduling(@RequestBody SchedulingRequest requestDto){
        return ResponseEntity.ok(service.createScheduling(requestDto));
    }

    @GetMapping("/{id}")
    @Timed(value = "scheduling.timed.getById")
    @Operation(description = "Endpoint responsible for find a notification schedule by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The notification schedule regarding the given id was successfully returned"),
            @ApiResponse(responseCode = "404", description = "No resource found with this id")
    })
    ResponseEntity<SchedulingResponse> getSchedulingById(@PathVariable Long id){
        return ResponseEntity.ok(service.getSchedulingById(id));
    }

    @DeleteMapping("/{id}")
    @Timed(value = "scheduling.timed.cancelById")
    @Operation(description = "Endpoint responsible for change the notification schedule status to CANCELED by the given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notification schedule successfully canceled"),
            @ApiResponse(responseCode = "404", description = "No resource found with this id")

    })
    ResponseEntity<Void> cancelSchedulingById(@PathVariable Long id){
        service.cancelSchedulingById(id);
        return ResponseEntity.accepted().build();
    }
}
