package view;

import model.Message;
import model.NewMessage;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class View {
    private JFrame frame;
    private BlockingQueue<Message> queue;

    public static View init(BlockingQueue<Message> queue) {
        // Create object of type view
        return new View(queue);
    }

    private View(BlockingQueue<Message> queue) {
        this.queue = queue;
        // TODO:
        // you should initalize JFrame and show it,
        // JFrame should be able to add Messages to queue
        // JFrame can be in a separate class or created JFrame with all the elements in this class
        // or you can make View a subclass of JFrame by extending it
        frame = new JFrame();

        JButton newGame = new JButton("New Game");
        JButton hitButton = new JButton("hit");

        newGame.addActionListener(event -> {
            try {
                this.queue.put(new NewMessage()); // <--- adding NewGame message to the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        hitButton.addActionListener(event -> {
            try {
                this.queue.put(new NewMessage()); // <--- adding Hit message to the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // add everything and set layout and other standard JFrame settings
        frame.add(newGame);
        frame.add(hitButton);
        frame.pack();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public void change() {
        // TODO: do all the updates and repaint
        //gameFrame.repaint();
    }

    public void dispose() {
        // TODO: clear all the resources
        // for example, gameFrame.dispose();
    }
}
