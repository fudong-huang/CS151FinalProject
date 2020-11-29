package model;


/**
 * Model class
 */
public class Model {
    ToDoList toDoList;

    /**
     * Constructor
     * @param toDoList A ToDoList
     */
    public Model(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public Model() {
        this.toDoList = new ToDoList();
    }

    /**
     * get todolist
     * @return ToDOList
     */
    public ToDoList getToDoList() {
        return toDoList;
    }
}
