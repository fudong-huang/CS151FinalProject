package controller;

import model.Message;
import model.Model;
import view.View;

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

}
