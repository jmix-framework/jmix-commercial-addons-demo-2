package com.company.demo.view.main;

import com.company.demo.entity.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import io.jmix.core.MetadataTools;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.component.applayout.JmixAppLayout;
import io.jmix.flowui.component.image.JmixImage;
import io.jmix.flowui.component.image.JmixImageVariant;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import static com.vaadin.flow.theme.lumo.LumoUtility.*;

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
    @Autowired
    protected UiComponents uiComponents;

    @ViewComponent
    protected Section section;

    @Subscribe
    protected void onInit(final InitEvent event) {
        section.addComponentAsFirst(createMainViewRouterlink());
    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        JmixAppLayout content = getContent();
        if (getContent().getContent() == null) {
            content.setContent(buildWelcomeContent());
        }
    }

    protected Component buildWelcomeContent() {
        StringBuilder sb = new StringBuilder("<div>");

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
        sb.append("</div>");
        Html html = new Html(sb.toString());
        html.addClassName(Padding.MEDIUM);
        return html;
    }

    protected RouterLink createMainViewRouterlink() {
        RouterLink link = uiComponents.create(RouterLink.class);
        link.setRoute(MainView.class);
        link.addClassNames("jmix-main-view-header-link", Display.FLEX, Padding.SMALL);

        JmixImage<String> image = uiComponents.create(JmixImage.class);
        image.setSrc("/icons/icon.png");
        image.addThemeVariants(JmixImageVariant.SCALE_DOWN);
        image.addClassNames(IconSize.LARGE, AlignSelf.CENTER);

        H2 header = uiComponents.create(H2.class);
        header.setText(messageBundle.getMessage("applicationTitle.text"));
        header.addClassNames("jmix-main-view-application-title", Whitespace.PRE_WRAP);

        link.add(image, header);

        return link;
    }
}
