package com.company.bpm.view.main;

import com.company.bpm.entity.User;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.Route;
import io.jmix.core.MetadataTools;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.flowui.view.*;
import io.jmix.tabbedmode.app.main.StandardTabbedModeMainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

@Route("")
@ViewController("MainView")
@ViewDescriptor("main-view.xml")
public class MainView extends StandardTabbedModeMainView {

    @Autowired
    private CurrentUserSubstitution currentUserSubstitution;
    @ViewComponent
    private MessageBundle messageBundle;
    @Autowired
    private MetadataTools metadataTools;
    @ViewComponent
    protected Section section;
    @ViewComponent
    private Div welcomeMessage;

    @Subscribe
    public void onReady(final ReadyEvent event) {
        buildWelcomeMessage();
    }

    private void buildWelcomeMessage() {
        StringBuilder sb = new StringBuilder();

        User user = (User) currentUserSubstitution.getEffectiveUser();
        sb.append(messageBundle.formatMessage("user", metadataTools.getInstanceName(user)));

        user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .forEach(authority -> {
                    String message = messageBundle.formatMessage(authority);
                    if (!message.equals(authority)) {
                        sb.append(message);
                    }
                });

        welcomeMessage.getElement().setProperty("innerHTML", sb.toString());
    }
}
