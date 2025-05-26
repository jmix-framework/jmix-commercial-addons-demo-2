package com.company.bpm.security;

import com.company.bpm.entity.*;
import io.jmix.bpmflowui.security.role.BpmProcessActorRole;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;


@ResourceRole(name = "HR Manager", code = HrManagerRole.CODE, scope = "UI")
public interface HrManagerRole extends BpmProcessActorRole, BasicAppRole {

    String CODE = "hr-manager";

    @MenuPolicy(menuIds = {"User.list", "WorkspaceRequest.list", "Department.list"})
    @ViewPolicy(viewIds = {
            "User.list", "User.detail",
            "WorkspaceRequest.list", "WorkspaceRequest.detail", "WorkspaceRequestReviewProcessForm",
            "Department.list", "Department.detail",
            "SoftwareRequest.detail", "SoftwareRequestProcessForm"})
    void views();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.ALL)
    void user();

    @EntityAttributePolicy(entityClass = Department.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Department.class, actions = EntityPolicyAction.READ)
    void department();

    @EntityAttributePolicy(entityClass = Software.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Software.class, actions = EntityPolicyAction.READ)
    void software();

    @EntityAttributePolicy(entityClass = WorkspaceRequest.class, attributes = "processLog", action = EntityAttributePolicyAction.VIEW)
    @EntityAttributePolicy(entityClass = WorkspaceRequest.class, attributes = {"id", "version", "date", "hrManager", "employee", "workType", "softwareRequests"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = WorkspaceRequest.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE, EntityPolicyAction.CREATE})
    void workspaceRequest();

    @EntityAttributePolicy(entityClass = SoftwareRequest.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = SoftwareRequest.class, actions = EntityPolicyAction.READ)
    void softwareRequest();
}