package com.company.tabbedmode.view.noncloseable;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.tabbedmode.ViewBuilders;
import io.jmix.tabbedmode.view.TabbedModeViewProperties;
import org.springframework.beans.factory.annotation.Autowired;

@TabbedModeViewProperties(closeable = false)
@Route(value = "non-closeable-view", layout = DefaultMainViewParent.class)
@ViewController(id = "NonCloseableView")
@ViewDescriptor(path = "non-closeable-view.xml")
public class NonCloseableView extends StandardView {

    @Autowired
    private ViewBuilders viewBuilders;

    @Subscribe(id = "openViewBtn", subject = "clickListener")
    public void onOpenViewBtnClick(final ClickEvent<JmixButton> event) {
        viewBuilders.view(this, NonCloseableView.class)
                .open();
    }

    @Subscribe(id = "closeBtn", subject = "clickListener")
    public void onCloseBtnClick(final ClickEvent<JmixButton> event) {
        closeWithDefaultAction();
    }
}