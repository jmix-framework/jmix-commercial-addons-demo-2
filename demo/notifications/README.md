# Jmix Notifications Demo

## Overview

The application's data model includes the [Order](src/main/java/com/company/notifications/entity/Order.java) entity.

[OrderEventListener](src/main/java/com/company/notifications/listener/OrderEventListener.java) sends a notification to `admin` user when an order is created.

The [main-view](src/main/resources/com/company/notifications/view/main/main-view.xml) contains the `notificationsIndicator` UI component that shows the number of received notifications for the current user.

## Demo Scenario

1. Run the application and go to <http://localhost:8105> in your browser.
2. Log in as `admin` with password `admin`.
3. Open the *Application → Orders* view.
4. Create a new order.
5. Check the number of received notifications in indicator located in the top right corner of the screen.

## See Also

- [Notifications Documentation](https://docs.jmix.io/jmix/notifications/index.html)
- [Notifications on Marketplace](https://www.jmix.io/marketplace/notifications/)
 
