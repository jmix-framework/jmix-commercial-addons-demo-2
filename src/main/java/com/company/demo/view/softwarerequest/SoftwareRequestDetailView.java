package com.company.demo.view.softwarerequest;

import com.company.demo.entity.SoftwareRequest;

import com.company.demo.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "softwareRequests/:id", layout = MainView.class)
@ViewController("SoftwareRequest.detail")
@ViewDescriptor("software-request-detail-view.xml")
@EditedEntityContainer("softwareRequestDc")
public class SoftwareRequestDetailView extends StandardDetailView<SoftwareRequest> {
}