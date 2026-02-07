package com.example.communication_scheduling_api.observability.actuator;


import com.example.communication_scheduling_api.infrastructure.repositories.SchedulingRepository;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomMetricsConfigs {

    @Bean
    public MeterBinder schedulingQuantity(SchedulingRepository repository){
        return (meterRegistry) -> Gauge.builder(
                "scheduling.quantity", repository::count).register(meterRegistry);
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry meterRegistry) {
        return new TimedAspect(meterRegistry);
    }
}
