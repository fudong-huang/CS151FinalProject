package view;

import model.Message;
import model.NewMessage;

import java.awt.event.ActionEvent;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *  NewListener class
 */
public class NewListener implements ActionListener{

    private BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    /**
     * Constructor
     * @param queue LinkedBlockingQueue
     */
    public NewListener(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    /**
     * Override actionPerformed method
     * @param event ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            queue.put(new NewMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
