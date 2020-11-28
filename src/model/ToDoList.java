package model;

import java.util.ArrayList;
import java.util.List;
/**
 * ToDoList class
 */
public class ToDoList {
    List<Task> toDoTaskList;
    List<Task> finishedTaskList;
    private Task selectedTask;

    public ToDoList() {
        this.toDoTaskList = new ArrayList<>();
        this.finishedTaskList = new ArrayList<>();
        this.selectedTask = new Task();
    }

    public List<Task> getToDoTaskList() {
        return toDoTaskList;
    }

    public List<Task> getFinishedTaskList() {
        return finishedTaskList;
    }

    public void addTodoTask(Task task) {
        toDoTaskList.add(task);
    }

    public void addFinishedTask(Task task) {
        finishedTaskList.add(task);
    }

    public void removeTodoTask(Task task) {
        toDoTaskList.remove(task);
    }

    public Task getSelectedTask() {
        return this.selectedTask;
    }

    public void setSelectedTask(Task task) {
        this.selectedTask = task;
    }
}
