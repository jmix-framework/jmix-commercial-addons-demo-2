package com.company.bpm.view.workspacerequest;

import com.company.bpm.entity.User;
import com.company.bpm.entity.WorkspaceRequest;
import com.company.bpm.security.HrManagerRole;
import com.company.bpm.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.security.role.RoleGrantedAuthorityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

@Route(value = "workspaceRequests", layout = MainView.class)
@ViewController("WorkspaceRequest.list")
@ViewDescriptor("workspace-request-list-view.xml")
@LookupComponent("workspaceRequestsDataGrid")
@DialogMode(width = "64em")
public class WorkspaceRequestListView extends StandardListView<WorkspaceRequest> {

    @Autowired
    private ViewNavigators viewNavigators;
    @Autowired
    private CurrentUserSubstitution currentUserSubstitution;
    @ViewComponent
    private DataGrid<WorkspaceRequest> workspaceRequestsDataGrid;
    @ViewComponent
    private JmixButton startProcessBtn;
    @Autowired
    private RoleGrantedAuthorityUtils roleGrantedAuthorityUtils;

    @Subscribe
    public void onInit(final InitEvent event) {
        User user = (User) currentUserSubstitution.getEffectiveUser();

        // Enable starting process if the user has "HR Manager" role
        boolean isHrManager = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(name -> name.equals(roleGrantedAuthorityUtils.getDefaultRolePrefix() + HrManagerRole.CODE));
        startProcessBtn.setEnabled(isHrManager);
    }

    @Subscribe("startProcessBtn")
    public void onStartProcessBtnClick(final ClickEvent<JmixButton> event) {
        // Show WorkspaceRequest edit screen with a new entity and start process when the entity is saved
        viewNavigators.detailView(workspaceRequestsDataGrid)
                .newEntity()
                .withViewClass(WorkspaceRequestDetailView.class)
                .withBackwardNavigation(true)
                .navigate();
    }
}