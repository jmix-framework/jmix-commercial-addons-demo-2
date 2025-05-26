package com.company.kanban.view.kanbanboard;


import com.company.kanban.entity.KanbanTask;
import com.company.kanban.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.view.*;
import io.jmix.kanbanflowui.component.Kanban;
import io.jmix.kanbanflowui.kit.component.event.KanbanTaskDoubleClickEvent;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "kanban-board", layout = MainView.class)
@ViewController("KanbanBoard")
@ViewDescriptor("kanban-board-view.xml")
public class KanbanBoard extends StandardView {

    @ViewComponent
    private Kanban<KanbanTask> kanban;

    @Autowired
    private DialogWindows dialogWindows;

    @Subscribe("kanban")
    public void onKanbanKanbanTaskDoubleClick(final KanbanTaskDoubleClickEvent<KanbanTask> event) {
        dialogWindows.detail(kanban).open();
    }
}