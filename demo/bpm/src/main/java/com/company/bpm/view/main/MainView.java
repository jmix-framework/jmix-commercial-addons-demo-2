package com.company.bpm.view.main;

import com.company.bpm.entity.User;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.Route;
import io.jmix.core.MetadataTools;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import static com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@Route("")
@ViewController("MainView")
@ViewDescriptor("main-view.xml")
public class MainView extends StandardMainView {

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

        sb.append(messageBundle.getMessage("end-note"));

        welcomeMessage.getElement().setProperty("innerHTML", sb.toString());
        welcomeMessage.addClassName(Padding.MEDIUM);
    }
}
