package JUnitTest;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class ToDoListTest {

    ToDoList toDoList = new ToDoList();
    List<Task> toDoTaskList;
    List<Task> finishedTaskList;
    Task task1 = new Task("task1", LocalDate.now());
    Task task2 = new Task("task2", LocalDate.now());
    Task task3 = new Task("task3", LocalDate.now());
    Task selectedTask;

    public ToDoListTest() {
        toDoTaskList = new ArrayList<>();
        finishedTaskList = new ArrayList<>();
        toDoList.setSelectedTask(task3);
    }

    @Test
    void getToDoTaskList() {
        assertEquals(toDoTaskList, toDoList.getToDoTaskList());
    }

    @Test
    void getFinishedTaskList() {
        assertEquals(finishedTaskList, toDoList.getFinishedTaskList());
    }

    @Test
    void addTodoTask() {
        toDoTaskList.add(task2);
        toDoList.getToDoTaskList().add(task2);
        assertEquals(toDoTaskList,  toDoList.getToDoTaskList());
    }

    @Test
    void addFinishedTask() {
        finishedTaskList.add(task1);
        toDoList.getFinishedTaskList().add(task1);
        assertEquals(finishedTaskList, toDoList.getFinishedTaskList());
    }

    @Test
    void removeTodoTask() {
        toDoTaskList.remove(task2);
        toDoList.removeTodoTask(task2);
        assertEquals(toDoTaskList,  toDoList.getToDoTaskList());
    }

    @Test
    void getSelectedTask() {
        assertEquals(task3, toDoList.getSelectedTask());
    }

    @Test
    void setSelectedTask() {
        toDoList.setSelectedTask(task1);
        assertEquals(task1, toDoList.getSelectedTask());
    }
}