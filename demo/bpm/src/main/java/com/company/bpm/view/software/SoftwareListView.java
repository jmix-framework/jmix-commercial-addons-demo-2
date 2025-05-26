package com.company.bpm.view.software;

import com.company.bpm.entity.Software;

import com.company.bpm.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "softwares", layout = MainView.class)
@ViewController("Software.list")
@ViewDescriptor("software-list-view.xml")
@LookupComponent("softwaresDataGrid")
@DialogMode(width = "64em")
public class SoftwareListView extends StandardListView<Software> {
}