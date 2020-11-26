package controller;

import model.Message;
import model.Model;
import model.NextMonthMessage;
import model.PrevMonthMessage;
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

        }
    }
}
