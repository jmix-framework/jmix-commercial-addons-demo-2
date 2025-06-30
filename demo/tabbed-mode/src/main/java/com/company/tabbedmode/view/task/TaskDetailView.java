package com.company.tabbedmode.view.task;

import com.company.tabbedmode.entity.Task;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "tasks/:id", layout = DefaultMainViewParent.class)
@ViewController(id = "Task_.detail")
@ViewDescriptor(path = "task-detail-view.xml")
@EditedEntityContainer("taskDc")
public class TaskDetailView extends StandardDetailView<Task> {
}