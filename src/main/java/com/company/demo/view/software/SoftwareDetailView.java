package com.company.demo.view.software;

import com.company.demo.entity.Software;

import com.company.demo.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "softwares/:id", layout = MainView.class)
@ViewController("Software.detail")
@ViewDescriptor("software-detail-view.xml")
@EditedEntityContainer("softwareDc")
public class SoftwareDetailView extends StandardDetailView<Software> {
}