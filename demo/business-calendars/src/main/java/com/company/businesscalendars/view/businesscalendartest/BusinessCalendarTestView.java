package com.company.businesscalendars.view.businesscalendartest;


import com.company.businesscalendars.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.router.Route;
import io.jmix.businesscalendar.model.BusinessCalendar;
import io.jmix.businesscalendar.repository.BusinessCalendarRepository;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Route(value = "business-calendar-test-view", layout = MainView.class)
@ViewController(id = "BusinessCalendarTestView")
@ViewDescriptor(path = "business-calendar-test-view.xml")
public class BusinessCalendarTestView extends StandardView {

    @Autowired
    private BusinessCalendarRepository businessCalendarRepository;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private Notifications notifications;

    @ViewComponent
    private JmixSelect<BusinessCalendar> calendarSelect;
    @ViewComponent
    private TypedDateTimePicker<LocalDateTime> dateTimePicker;

    @Subscribe
    public void onReady(final ReadyEvent event) {
        Collection<BusinessCalendar> calendars = businessCalendarRepository.getAllBusinessCalendars();
        calendarSelect.setItems(calendars);
        calendarSelect.setItemLabelGenerator(BusinessCalendar::getName);
    }

    @Subscribe(id = "testButton", subject = "clickListener")
    public void onTestButtonClick(final ClickEvent<JmixButton> event) {
        BusinessCalendar businessCalendar = calendarSelect.getValue();
        if (businessCalendar == null) {
            notifications.show("Select a business calendar");
            return;
        }

        LocalDateTime dateTime = dateTimePicker.getValue();
        if (dateTime == null) {
            notifications.show("Select a date and time");
            return;
        }

        boolean businessDay = businessCalendar.isBusinessDay(dateTime.toLocalDate());
        boolean businessTime = businessCalendar.isBusinessTime(dateTime);
        LocalDate datePlus5Days = businessCalendar.plus(dateTime.toLocalDate(), 5);
        LocalDateTime timePlus5Hours = businessCalendar.plus(dateTime, Duration.ofHours(5));

        dialogs.createMessageDialog()
                .withHeader(dateTime.toString())
                .withContent(new Html("<p>" +
                        "Business day: " + businessDay + "<br>" +
                        "Business time: " + businessTime + "<br>" +
                        "Plus 5 business days: " + datePlus5Days.toString() + "<br>" +
                        "Plus 5 business hours: " + timePlus5Hours.toString() + "<br>" +
                        "</p>"))
                .open();
    }


}