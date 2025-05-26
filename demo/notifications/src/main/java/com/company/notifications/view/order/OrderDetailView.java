package com.company.notifications.view.order;

import com.company.notifications.entity.Order;
import com.company.notifications.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "orders/:id", layout = MainView.class)
@ViewController(id = "Order_.detail")
@ViewDescriptor(path = "order-detail-view.xml")
@EditedEntityContainer("orderDc")
public class OrderDetailView extends StandardDetailView<Order> {

    @Subscribe
    public void onInit(final InitEvent event) {
        setShowSaveNotification(false);
    }
}