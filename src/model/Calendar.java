package model;


import java.time.LocalDate;

/**
 * Calendar class
 */
public class Calendar {
    private LocalDate firstDay;
    private LocalDate date;

    public LocalDate getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(LocalDate firstDay) {
        this.firstDay = firstDay;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
