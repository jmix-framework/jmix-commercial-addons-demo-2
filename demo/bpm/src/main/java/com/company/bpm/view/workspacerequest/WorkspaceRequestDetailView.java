package com.company.bpm.view.workspacerequest;

import com.company.bpm.entity.SoftwareRequest;
import com.company.bpm.entity.User;
import com.company.bpm.entity.WorkspaceRequest;
import com.company.bpm.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.EntityStates;
import io.jmix.core.TimeSource;
import io.jmix.core.common.util.ParamsMap;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.view.*;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@Route(value = "workspaceRequests/:id", layout = MainView.class)
@ViewController("WorkspaceRequest.detail")
@ViewDescriptor("workspace-request-detail-view.xml")
@EditedEntityContainer("workspaceRequestDc")
public class WorkspaceRequestDetailView extends StandardDetailView<WorkspaceRequest> {

    @Autowired
    private CurrentUserSubstitution currentUserSubstitution;

    @Autowired
    private TimeSource timeSource;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private EntityStates entityStates;
    @ViewComponent
    private CollectionPropertyContainer<SoftwareRequest> softwareRequestsDc;
    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<WorkspaceRequest> event) {
        WorkspaceRequest workspaceRequest = event.getEntity();
        workspaceRequest.setHrManager((User) currentUserSubstitution.getEffectiveUser());
        workspaceRequest.setDate(timeSource.now().toLocalDate());
    }

    @Install(to = "softwareRequestsDataGrid.create", subject = "initializer")
    private void softwareRequestsDataGridCreateInitializer(final SoftwareRequest softwareRequest) {
        // Assign sequential numbers when creating SoftwareRequest instances
        Integer maxSortValue = softwareRequestsDc.getItems().stream()
                .map(SoftwareRequest::getSortValue)
                .max(Integer::compareTo)
                .orElse(0);
        softwareRequest.setSortValue(maxSortValue + 1);
    }

    @Subscribe("completeBtn")
    public void onCompleteBtnClick(final ClickEvent<JmixButton> event) {
        WorkspaceRequest request = getEditedEntity();
        boolean newRequest = entityStates.isNew(request);
        save()
                .then(() -> {
                    if (newRequest) { // Start process for the new request
                        startProcess(request);
                    }
                    close(StandardOutcome.SAVE);
                });
    }

    private void startProcess(WorkspaceRequest workspaceRequest) {
        String businessKey = workspaceRequest.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + " - "
                + workspaceRequest.getEmployee().getFirstName() + " "
                + workspaceRequest.getEmployee().getLastName();
        Map<String, Object> variables = ParamsMap.of(
                "date", workspaceRequest.getDate(),
                "employee", workspaceRequest.getEmployee(),
                "workspaceRequest", workspaceRequest);
        runtimeService.startProcessInstanceByKey("workspace-preparation", businessKey, variables);
        notifications.create("Process started")
                .withType(Notifications.Type.SUCCESS)
                .withPosition(Notification.Position.TOP_END)
                .show();
    }

}