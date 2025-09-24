package com.example.communication_scheduling_api.business.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;

import java.time.LocalDateTime;

public record SchedulingRequest(@NonNull
                                String message,
                                @NonNull
                                String recipientEmail,
                                String recipientPhone,
                                @NonNull
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                LocalDateTime deliveryDateTime) {
}
