package model;


/**
 * Model class
 */
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
