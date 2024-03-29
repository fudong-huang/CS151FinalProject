package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

/**
 * A new frame that shows the ToDoList UI
 */
public class ToDoListView extends JFrame {
    private JFrame frame;
    private View view;
    private BlockingQueue<Message> queue;
    private Model model;

    /**
     * Constructor
     * @param view
     */
    public ToDoListView(View view) {
        this.frame = new JFrame();
        frame.setBounds(800, 0, 450, 300);
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

    /**
     * Add some components to pane
     * @param pane a pane of container
     */
    private void addComponentsToPane(Container pane) {
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel jPanel1 = new JPanel();
        JLabel title = new JLabel("ToDoTasks");

        jPanel1.add(title);

        for (Task task : model.getToDoList().getToDoTaskList()) {
            if (task.getDate().equals(view.getSelectedDate())) {
                JCheckBox jCheckBox = new JCheckBox(task.getContent());
                jCheckBox.addActionListener(event -> {
                    try {
                        model.getToDoList().setSelectedTask(task);
                        System.out.println("print select task: " + task.getContent().toString());
                        queue.put(new AddFinishedTaskMessage());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                jPanel1.add(jCheckBox);
            }
        }

        c.weightx = 5;
        c.weighty = 5;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(jPanel1, c);

        JPanel jPanel2 = new JPanel();
        JLabel title2 = new JLabel("FinishedTask");
        jPanel2.add(title2);
        for (Task task: model.getToDoList().getFinishedTaskList()) {
            if (task.getDate().equals(view.getSelectedDate())) {
                JLabel jLabel = new JLabel(task.getContent());
                jPanel2.add(jLabel);
            }
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(jPanel2, c);


        JPanel jPanel3 = new JPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.ABOVE_BASELINE;
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
                view.setInputStr(jTextField.getText());
                view.getSelectedButton().setForeground(Color.BLUE);
                queue.put(new SaveToDoListMessage());
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

    /**
     * update the view
     * @param model the date model to paint
     */
    public void update(Model model) {
        frame.getContentPane().removeAll();
        this.model = model;
        addComponentsToPane(frame.getContentPane());
        System.out.println("calling repaint at todolist view");
        frame.getContentPane().revalidate();
        frame.repaint();
    }


}
