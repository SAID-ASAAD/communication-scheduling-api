package com.example.communication_scheduling_api.controllers.dtos;

import com.example.communication_scheduling_api.infrastructure.enuns.SchedulingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;

import java.time.LocalDateTime;

public record SchedulingResponse(Long id,
                                 String message,
                                 String recipientEmail,
                                 String recipientPhone,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                 LocalDateTime deliveryDateTime,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                 LocalDateTime schedulingDateTime,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                 LocalDateTime modificationDateTime,
                                 SchedulingStatus schedulingStatus) {
}
