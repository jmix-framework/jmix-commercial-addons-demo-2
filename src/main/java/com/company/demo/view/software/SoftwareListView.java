package com.company.demo.view.software;

import com.company.demo.entity.Software;

import com.company.demo.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "softwares", layout = MainView.class)
@ViewController("Software.list")
@ViewDescriptor("software-list-view.xml")
@LookupComponent("softwaresDataGrid")
@DialogMode(width = "64em")
public class SoftwareListView extends StandardListView<Software> {
}