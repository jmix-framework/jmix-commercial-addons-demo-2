package com.company.notifications.listener;

import com.company.notifications.NotificationTypes;
import com.company.notifications.entity.Order;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.notifications.NotificationManager;
import io.jmix.notifications.channel.impl.InAppNotificationChannel;
import io.jmix.notifications.entity.ContentType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderEventListener {

    private final NotificationManager notificationManager;
    private final DataManager dataManager;

    public OrderEventListener(NotificationManager notificationManager, DataManager dataManager) {
        this.notificationManager = notificationManager;
        this.dataManager = dataManager;
    }

    @TransactionalEventListener                             // Runs after transaction commit
    @Transactional(propagation = Propagation.REQUIRES_NEW)  // Starts new transaction to load data
    public void onOrderChangedAfterCommit(final EntityChangedEvent<Order> event) {
        if (event.getType() == EntityChangedEvent.Type.CREATED) {
            Order order = dataManager.load(event.getEntityId()).one();
            notificationManager.createNotification()
                    .withSubject("Created order")
                    .withRecipientUsernames("admin")
                    .toChannelsByNames(InAppNotificationChannel.NAME)
                    .withTypeName(NotificationTypes.INFO)
                    .withContentType(ContentType.PLAIN)
                    .withBody("Order number: " + order.getNumber() + ", date: " + order.getDate())
                    .send();
        }
    }
}