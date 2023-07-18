package com.company.demo.view.main;

import com.company.demo.entity.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.router.Route;
import io.jmix.core.MetadataTools;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.view.MessageBundle;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

@Route("")
@ViewController("MainView")
@ViewDescriptor("main-view.xml")
public class MainView extends StandardMainView {

    @Autowired
    private CurrentUserSubstitution currentUserSubstitution;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private MetadataTools metadataTools;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        getContent().setContent(buildWelcomeContent());
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        super.showRouterLayoutContent(content);
        if (content == null) {
            getContent().setContent(buildWelcomeContent());
        }
    }

    protected Component buildWelcomeContent() {
        StringBuilder sb = new StringBuilder("<div>");

        User user = (User) currentUserSubstitution.getEffectiveUser();
        sb.append(messageBundle.formatMessage("user", metadataTools.getInstanceName(user)));

        String key = "role-";
        user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .forEach(role -> {
                    String message = messageBundle.formatMessage(key + role);
                    if (!message.equals(key + role)) {
                        sb.append(message);
                    }
                });

        sb.append(messageBundle.getMessage("end-note"));
        sb.append("</div>");
        /*Div div = new Div();
        div.addClassName("p-m");*/
        Html html = new Html(sb.toString());
        html.addClassName("p-m");
        //div.add(html);
        return html;
    }
}
