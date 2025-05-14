/*
 * Copyright 2023 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.company.bpm.view.softwarerequest;


import com.company.bpm.entity.SoftwareRequest;
import com.company.bpm.entity.WorkspaceRequest;
import com.company.bpm.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(allowedProcessKeys = {"workspace-preparation"})
@Route(value = "SoftwareRequestProcessForm", layout = MainView.class)
@ViewController("SoftwareRequestProcessForm")
@ViewDescriptor("software-request-process-form.xml")
@DialogMode(width = "64em")
public class SoftwareRequestProcessForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ViewComponent
    private DataContext dataContext;
    @ProcessVariable
    protected WorkspaceRequest workspaceRequest;
    @ViewComponent
    private InstanceContainer<WorkspaceRequest> workspaceRequestDc;
    @ViewComponent
    private CollectionPropertyContainer<SoftwareRequest> softwareRequestsDc;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        workspaceRequestDc.setItem(workspaceRequest);
    }

    @Subscribe
    public void onInit(final InitEvent event) {
        if (workspaceRequest != null) {
            workspaceRequestDc.setItem(workspaceRequest);
        }
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
        // Save changes and complete the task
        workspaceRequestDc.getItem();
        dataContext.save();
        processFormContext.taskCompletion().complete();
        close(StandardOutcome.SAVE);
    }
}