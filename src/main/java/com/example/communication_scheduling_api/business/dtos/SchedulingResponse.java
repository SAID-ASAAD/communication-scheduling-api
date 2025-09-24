package com.example.communication_scheduling_api.business.dtos;

import com.example.communication_scheduling_api.infrastructure.enuns.SchedulingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;

import java.time.LocalDateTime;

public record SchedulingResponse(String message,
                                 String recipientEmail,
                                 String recipientPhone,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                 LocalDateTime deliveryDateTime,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                 LocalDateTime schedulingDateTime,
                                 SchedulingStatus schedulingStatus) {
}
