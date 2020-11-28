package JUnitTest;

import model.Calendar;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for Calendar class
 */
class CalendarTest {
    Calendar calendar;


    public CalendarTest() {
        calendar = new Calendar();
        calendar.setFirstDay(LocalDate.now());
        calendar.setDate(LocalDate.now());
    }


    @Test
    void getFirstDay() {
        assertEquals(LocalDate.now(), calendar.getFirstDay());
    }

    @Test
    void setFirstDay() {
        LocalDate date =  LocalDate.of(2020,1,1);
        calendar.setFirstDay(date);
        assertEquals(date, calendar.getFirstDay());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.now(), calendar.getDate());
    }

    @Test
    void setDate() {
        LocalDate date =  LocalDate.of(2020,1,1);
        calendar.setDate(date);
        assertEquals(date, calendar.getDate());
    }
}