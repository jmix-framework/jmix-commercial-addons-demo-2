package com.company.notifications;

import io.jmix.notifications.NotificationType;
import io.jmix.notifications.NotificationTypesRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationTypes {

    public static final String INFO = "info";
    public static final String WARN = "warn";

    private final NotificationTypesRepository notificationTypesRepository;

    public NotificationTypes(NotificationTypesRepository notificationTypesRepository) {
        this.notificationTypesRepository = notificationTypesRepository;
    }

    @EventListener
    public void onApplicationContextRefreshed(final ContextRefreshedEvent event) {
        notificationTypesRepository.registerTypes(
                new NotificationType(INFO, "INFO_CIRCLE"),
                new NotificationType(WARN, "WARNING")
        );
    }
}
