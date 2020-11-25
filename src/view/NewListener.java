package view;

import model.NewMessage;

import java.awt.event.ActionEvent;

public class NewListener implements  ActionListener{

    public void actionPerformed(ActionEvent event) {
        try {
            queue.put(new NewMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
