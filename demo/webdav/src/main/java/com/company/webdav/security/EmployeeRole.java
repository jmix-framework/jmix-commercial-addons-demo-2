package com.company.webdav.security;

import com.company.webdav.entity.Article;
import com.company.webdav.entity.Department;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;
import io.jmix.webdavflowui.role.WebdavMinimalRole;

@ResourceRole(name = "EmployeeRole", code = EmployeeRole.CODE, scope = "UI")
public interface EmployeeRole extends WebdavMinimalRole {
    String CODE = "employee";

    @MenuPolicy(menuIds = {"Article.list", "Department.list"})
    @ViewPolicy(viewIds = {"Article.list", "Article.detail", "Department.list", "Department.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = Article.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Article.class, actions = EntityPolicyAction.ALL)
    void article();

    @EntityAttributePolicy(entityClass = Department.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Department.class, actions = EntityPolicyAction.ALL)
    void department();
}