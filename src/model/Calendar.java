package model;

import java.time.LocalDate;

public class Calendar {
    private LocalDate firstDay;
    private LocalDate date;

    /**
     * get FirstDay
     * @return LocalDate
     */
    public LocalDate getFirstDay() {
        return firstDay;
    }

    /**
     * setFirstDay
     * @param firstDay firstDay of the month
     */
    public void setFirstDay(LocalDate firstDay) {
        this.firstDay = firstDay;
    }

    /**
     * get Date
     * @return LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * set date
     * @param date set the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
