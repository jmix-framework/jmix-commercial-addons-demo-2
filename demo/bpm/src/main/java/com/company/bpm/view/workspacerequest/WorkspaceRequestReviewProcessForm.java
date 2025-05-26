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

package com.company.bpm.view.workspacerequest;


import com.company.bpm.entity.WorkspaceRequest;
import com.company.bpm.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm
@Route(value = "WorkspaceRequestReviewProcessForm", layout = MainView.class)
@ViewController("WorkspaceRequestReviewProcessForm")
@ViewDescriptor("workspace-request-review-process-form.xml")
@DialogMode(width = "64em")
public class WorkspaceRequestReviewProcessForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ProcessVariable
    protected WorkspaceRequest workspaceRequest;
    @ViewComponent
    private InstanceContainer<WorkspaceRequest> workspaceRequestDc;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        workspaceRequestDc.setItem(workspaceRequest);
    }

    @Subscribe("completeBtn")
    public void onCompleteBtnClick(final ClickEvent<JmixButton> event) {
        // Complete the task
        processFormContext.taskCompletion().complete();
        close(StandardOutcome.SAVE);
    }
}