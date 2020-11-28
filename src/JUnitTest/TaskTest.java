package JUnitTest;

import model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for Task class
 */
class TaskTest {
    String content = "testString";
    LocalDate date = LocalDate.now();
    Task task = new Task(content, date);

    @Test
    void getContent() {
        assertEquals(content, task.getContent());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.now(), date);
    }
}