package com.company.businesscalendars;

import io.jmix.businesscalendar.annotation.*;

import java.time.DayOfWeek;
import java.time.Month;

@BusinessCalendar(name = "Retail Business Calendar 2025", code = "retail-2025")
public interface RetailCalendar2025 {

    @WeeklyHoliday(DayOfWeek.SATURDAY)
    @WeeklyHoliday(value = DayOfWeek.SUNDAY, description = "weekend")
    void weeklyHolidays();

    @CronHoliday(expression = "* * * 1 JAN ?")
    void newYearDay();

    @FixedDayHoliday(fixedDate = "2025-04-18")
    void goodFriday();

    @FixedYearlyHoliday(month = Month.DECEMBER, dayOfMonth = 25)
    void christmasHoliday();

    @ScheduledBusinessDay(dayOfWeek = DayOfWeek.MONDAY, startTime = "09:00", endTime = "18:00")
    @ScheduledBusinessDay(dayOfWeek = DayOfWeek.TUESDAY, startTime = "09:00", endTime = "18:00")
    @ScheduledBusinessDay(dayOfWeek = DayOfWeek.WEDNESDAY, startTime = "09:00", endTime = "18:00")
    @ScheduledBusinessDay(dayOfWeek = DayOfWeek.THURSDAY, startTime = "09:00", endTime = "18:00")
    @ScheduledBusinessDay(dayOfWeek = DayOfWeek.FRIDAY, startTime = "09:00", endTime = "18:00")
    void scheduledBusinessDays();

    @AdditionalBusinessDay(fixedDate = "2025-11-28", startTime = "09:00", endTime = "22:00")
    void blackFriday();

    @AdditionalBusinessDay(fixedDate = "2025-12-01", startTime = "09:00", endTime = "22:00")
    void cyberMonday();

    @AdditionalBusinessDay(fixedDate = "2025-12-27", startTime = "10:00", endTime = "18:00")
    @AdditionalBusinessDay(fixedDate = "2025-12-28", startTime = "10:00", endTime = "18:00")
    void preNewYearSale();
}