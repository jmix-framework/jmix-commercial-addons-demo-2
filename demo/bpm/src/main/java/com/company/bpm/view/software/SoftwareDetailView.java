package com.company.bpm.view.software;

import com.company.bpm.entity.Software;

import com.company.bpm.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "softwares/:id", layout = MainView.class)
@ViewController("Software.detail")
@ViewDescriptor("software-detail-view.xml")
@EditedEntityContainer("softwareDc")
public class SoftwareDetailView extends StandardDetailView<Software> {
}