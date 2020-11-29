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

    /**
     * get ToDoTask list
     * @return List of Task
     */
    public List<Task> getToDoTaskList() {
        return toDoTaskList;
    }

    /**
     * get FinishedTaskList
     * @return list of Task
     */
    public List<Task> getFinishedTaskList() {
        return finishedTaskList;
    }

    /**
     * add Task to ToDoTask List
     * @param task the Task to add
     */
    public void addTodoTask(Task task) {
        toDoTaskList.add(task);
    }

    /**
     * add Task to FinishedTask List
     * @param task the task to add
     */
    public void addFinishedTask(Task task) {
        finishedTaskList.add(task);
    }

    /**
     * remove Todotask from the list
     * @param task the Task to remove
     */
    public void removeTodoTask(Task task) {
        toDoTaskList.remove(task);
    }

    /**
     * get selected task
     * @return selected task
     */
    public Task getSelectedTask() {
        return this.selectedTask;
    }

    /**
     * set selected task
     * @param task the task to set
     */
    public void setSelectedTask(Task task) {
        this.selectedTask = task;
    }
}
