package controller;

import model.*;
import view.ToDoListView;
import view.View;

import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;

public class Controller {
    private BlockingQueue<Message> queue;
    private View view;
    private Model model;


    public Controller(View view, Model model, BlockingQueue<Message> queue) {
        this.view = view;
        this.model = model;
        this.queue = queue;
    }

    public void mainLoop() throws InterruptedException
    {
        Message message = null;
        while(true) {
            try {
                message = queue.take();
                System.out.println("messgae class: " + message.getClass());
                execute(message);
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void execute(Message message) {
        if (message instanceof PrevMonthMessage) {
            System.out.println("prevMonth");
            LocalDate date = view.getLocalDate();
            date = date.minusMonths(1);
            System.out.println(date.toString());
            view.update(date);
        } else if (message instanceof NextMonthMessage) {
            System.out.println("nextMonth");
            LocalDate date = view.getLocalDate();
            date = date.plusMonths(1);
            System.out.println(date.toString());
            view.update(date);
        } else if (message instanceof ToDoListMessage) {
            System.out.println("To Do list");
            view.updateTodoListView(model);
        } else if (message instanceof SaveToDoListMessage) {
           Task task = new Task(view.getInputStr(), view.getLocalDate());
           System.out.println("printing task str" + task.getContent());
           model.getToDoList().addTodoTask(task);
           view.updateTodoListView(model);
        } else if (message instanceof  AddFinishedTaskMessage) {
            Task selectedTask = model.getToDoList().getSelectedTask();
            model.getToDoList().removeTodoTask(selectedTask);
            model.getToDoList().addFinishedTask(selectedTask);
            view.updateTodoListView(model);
        }
    }
}
