package JUnitTest;

import model.Model;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for Model class
 */
class ModelTest {

    Model model = new Model();
    ToDoList toDoList =  model.getToDoList();

    @Test
    void getToDoList() {
        assertEquals( toDoList, model.getToDoList());
    }
}