package JUnitTest;

import model.Model;
import model.ToDoList;
import org.junit.jupiter.api.Test;


import javax.swing.tree.TreeNode;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Model model = new Model();
    ToDoList toDoList =  model.getToDoList();

    @Test
    void getToDoList() {
        assertEquals( toDoList, model.getToDoList());
    }
}