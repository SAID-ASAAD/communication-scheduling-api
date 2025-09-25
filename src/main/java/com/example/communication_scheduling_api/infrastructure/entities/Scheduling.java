package com.example.communication_scheduling_api.infrastructure.entities;

import com.example.communication_scheduling_api.infrastructure.enuns.SchedulingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "scheduling")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String message;
    @Column(name = "recipient_email", nullable = false)
    private String recipientEmail;
    @Column(name = "recipient_phone")
    private String recipientPhone;
    @Column(name = "delivery_date_time", nullable = false)
    private LocalDateTime deliveryDateTime;
    @Column(name = "scheduling_date_time")
    private LocalDateTime schedulingDateTime;
    @Column(name = "modification_date_time")
    private LocalDateTime modificationDateTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "scheduling_status")
    private SchedulingStatus schedulingStatus;

    @PrePersist
    private void prePersist(){
        this.schedulingDateTime = LocalDateTime.now();
        this.schedulingStatus = SchedulingStatus.SCHEDULED;
    }


}
