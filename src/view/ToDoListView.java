package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;


public class ToDoListView extends JFrame {
    private JFrame frame;
    private View view;
    private BlockingQueue<Message> queue;
    private Model model;


    public ToDoListView(View view) {
        this.frame = new JFrame();
        this.view = view;
        this.queue = view.getQueue();
        this.model = new Model();
        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setTitle("ToDoList");
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private void addComponentsToPane(Container pane) {
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel jPanel1 = new JPanel();
        JLabel title = new JLabel("ToDoTasks");

        jPanel1.add(title);
        for (Task task : model.getToDoList().getToDoTaskList()) {
            JCheckBox jCheckBox = new JCheckBox(task.getContent());
            System.out.println(task.getContent());
            jCheckBox.addActionListener(event -> {
                try {
                    queue.put(new AddFinishedTaskMessage());
                    model.getToDoList().setSelectedTask(task);
                    System.out.println("print select task: " + task.getContent().toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            jPanel1.add(jCheckBox);
        }

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 5;
        c.weighty = 5;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(jPanel1, c);

        JPanel jPanel2 = new JPanel();
       // jPanel2.setSize(400,400);
        JLabel title2 = new JLabel("FinishedTask");

        JCheckBox jCheckBox2 = new JCheckBox();
        JLabel jLabel2 = new JLabel("bbb");
        jPanel2.add(title2);
        jPanel2.add(jCheckBox2);
        jPanel2.add(jLabel2);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(jPanel2, c);


        JPanel jPanel3 = new JPanel();
        jPanel3.setSize(50,50);
        c.gridx = 0;
        c.gridy = 5;
        JLabel date = new JLabel(view.getSelectedDate().toString());
        JTextField jTextField = new JTextField(25);
        jPanel3.add(date);
        jPanel3.add(jTextField);

        pane.add(jPanel3, c);

        JPanel jPanel4 = new JPanel();
        JButton jButton = new JButton("save");
        jButton.addActionListener(event ->{
            try {
                System.out.println("qsize: " + queue.size() );
                System.out.println("jTextField.getText(): " + jTextField.getText());
                view.setInputStr(jTextField.getText());
                queue.put(new SaveToDoListMessage()); // <--- adding NewGame message to the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        jPanel4.add(jButton);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.weightx = 0.5;
        c.gridx = 10;
        c.gridy = 10;
        pane.add(jPanel4, c);
    }

    public void update(Model model) {
        frame.getContentPane().removeAll();
        this.model = model;
        addComponentsToPane(frame.getContentPane());
        System.out.println("calling repaint at todolist view");
        frame.getContentPane().revalidate();
        frame.repaint();
    }


}
