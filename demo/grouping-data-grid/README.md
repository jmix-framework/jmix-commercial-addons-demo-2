# Jmix Grouping Data Grid Demo

## Overview

The application's data model includes the [Client](src/main/java/com/company/groupdatagrid/entity/Client.java) and [Order](src/main/java/com/company/groupdatagrid/entity/Order.java) entities.

The [client-list-view](src/main/resources/com/company/groupdatagrid/view/client/client-list-view.xml) and [order-list-view](src/main/resources/com/company/groupdatagrid/view/order/order-list-view.xml) display the entities using the `groupDataGrid` component.

## Demo Scenario

1. Run the application and go to <http://localhost:8108> in your browser.
2. Log in as `admin` with password `admin`.
3. Open the *Application → Clients* view. 
4. Click on the "grouping" icon in the first column. Add _City_ and _Type_ columns to the _Group by_ section.
5. Open the *Application → Orders* view.
6. You will see the data grid grouped by the _Client_ and _Status_  columns.

## See Also

- [Grouping Data Grid Documentation](https://docs.jmix.io/jmix/groupdatagrid/index.html)
- [Grouping Data Grid on Marketplace](https://www.jmix.io/marketplace/groupdatagrid/)
