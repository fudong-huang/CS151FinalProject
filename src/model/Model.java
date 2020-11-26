package model;

import java.util.Date;

public class Model {
    ToDoList toDoList;

    public Model(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public Model() {
        this.toDoList = new ToDoList();
    }

    public ToDoList getToDoList() {
        return toDoList;
    }
}
