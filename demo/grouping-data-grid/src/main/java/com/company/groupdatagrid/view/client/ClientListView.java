package com.company.groupdatagrid.view.client;

import com.company.groupdatagrid.entity.Client;
import com.company.groupdatagrid.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "clients", layout = MainView.class)
@ViewController(id = "Client.list")
@ViewDescriptor(path = "client-list-view.xml")
@LookupComponent("clientsDataGrid")
@DialogMode(width = "64em")
public class ClientListView extends StandardListView<Client> {
}