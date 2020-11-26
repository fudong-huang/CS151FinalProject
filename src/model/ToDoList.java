package model;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    List<ToDoTask> toDoTaskList;
    List<FinishedTask> finishedTaskList;

    public ToDoList(List<ToDoTask> toDoTaskList, List<FinishedTask> finishedTaskList) {
        this.toDoTaskList = toDoTaskList;
        this.finishedTaskList = finishedTaskList;
    }

    public ToDoList() {
        this.toDoTaskList = new ArrayList<>();
        this.finishedTaskList = new ArrayList<>();
    }
}
