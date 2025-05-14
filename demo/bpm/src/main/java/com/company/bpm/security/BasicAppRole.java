package com.company.bpm.security;

import io.jmix.bpmflowui.model.MyTasksTreeItem;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "Basic", code = BasicAppRole.CODE, scope = "UI")
public interface BasicAppRole {

    String CODE = "app-basic";

    @EntityPolicy(entityClass = MyTasksTreeItem.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = MyTasksTreeItem.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void bpm();
}