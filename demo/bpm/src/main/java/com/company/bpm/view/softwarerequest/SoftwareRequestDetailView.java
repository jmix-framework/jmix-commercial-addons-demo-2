package com.company.bpm.view.softwarerequest;

import com.company.bpm.entity.SoftwareRequest;

import com.company.bpm.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "softwareRequests/:id", layout = MainView.class)
@ViewController("SoftwareRequest.detail")
@ViewDescriptor("software-request-detail-view.xml")
@EditedEntityContainer("softwareRequestDc")
public class SoftwareRequestDetailView extends StandardDetailView<SoftwareRequest> {
}