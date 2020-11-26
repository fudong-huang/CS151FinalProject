package model;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    List<Task> toDoTaskList;
    List<Task> finishedTaskList;

    public ToDoList(List<Task> toDoTaskList, List<Task> finishedTaskList) {
        this.toDoTaskList = toDoTaskList;
        this.finishedTaskList = finishedTaskList;
    }

    public ToDoList() {
        this.toDoTaskList = new ArrayList<>();
        this.finishedTaskList = new ArrayList<>();
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
}
