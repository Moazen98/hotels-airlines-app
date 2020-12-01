package com.zeon.SpringBootAdminMicroServer.Config;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.CompositeNotifier;
import de.codecentric.boot.admin.server.notify.LoggingNotifier;
import de.codecentric.boot.admin.server.notify.Notifier;
import de.codecentric.boot.admin.server.notify.RemindingNotifier;
import de.codecentric.boot.admin.server.notify.filter.FilteringNotifier;


//By default a reminder is triggered when a registered application changes to DOWN or OFFLINE
//By default the reminders are sent every 10 minutes, to change this use setReminderPeriod()


@Configuration
public class NotifierConfiguration {
    private final InstanceRepository repository;
    private final ObjectProvider<List<Notifier>> otherNotifiers;
 
    public NotifierConfiguration(InstanceRepository repository, 
      ObjectProvider<List<Notifier>> otherNotifiers) {
        this.repository = repository;
        this.otherNotifiers = otherNotifiers;
    }
 
    @Bean
    public FilteringNotifier filteringNotifier() {
        CompositeNotifier delegate = 
          new CompositeNotifier(this.otherNotifiers.getIfAvailable(Collections::emptyList));
        return new FilteringNotifier(delegate, this.repository);
    }
 
    @Bean
    public LoggingNotifier notifier() {
        return new LoggingNotifier(repository);
    }
 
    @Primary
    @Bean(initMethod = "start", destroyMethod = "stop")
    public RemindingNotifier remindingNotifier() {
        RemindingNotifier remindingNotifier = new RemindingNotifier(filteringNotifier(), repository);
        remindingNotifier.setReminderPeriod(Duration.ofMinutes(5));  //	The reminders will be sent every 5 minutes.
        remindingNotifier.setCheckReminderInverval(Duration.ofSeconds(60));  //Schedules sending of due reminders every 60 seconds.

        return remindingNotifier;
    }
}