package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/**
 *
 */
public class View extends JFrame{
    private JFrame frame;
    private BlockingQueue<Message> queue;
    private LocalDate date;
    private LocalDate selectedDate;
    private ToDoListView toDoListView;
    private String inputStr;
    private JButton selectedButton;

    public static View init(BlockingQueue<Message> queue,LocalDate date) {
        // Create object of type view
        return new View(queue, date);
    }

    /**
     * Constructor
     * @param queue LinkedBlockingQueue for the producer and consumer
     * @param date  Date class
     */
    private View(BlockingQueue<Message> queue, LocalDate date) {
        this.date = date;
        this.queue = queue;
        this.frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        selectedDate = null;
        selectedButton = null;
        paint();
        frame.pack();
        frame.setTitle("Calendar&ToDoList");
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Print title method
     * @param calPanel Jpanel
     */
    private void printTitle(JPanel calPanel) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            String str = dayOfWeek.toString();
            JLabel label = new JLabel(str);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            calPanel.add(label);
        }
    }

    /**
     * Print the date of  previous month
     * @param jPanel Main Jpanel
     * @param date  Date
     * @param count count how many dates have been printed
     */
    private void printPrevMonth(JPanel jPanel, LocalDate date, int[] count) {
        LocalDate currentDate = LocalDate.of(date.getYear(), date.getMonth(), 1);
        int firstDay = currentDate.getDayOfWeek().getValue();
        if (firstDay != 7) {
            System.out.println(firstDay);
            for (int i = 0; i < firstDay; i++) {
                currentDate = currentDate.minusDays(1);
            }

            for(int i = 0; i < firstDay; i++) {
                JLabel label = new JLabel(Integer.toString(currentDate.getDayOfMonth()));
                label.setForeground(Color.GRAY);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                jPanel.add(label);
                currentDate = currentDate.plusDays(1);
                count[0]++;
            }
        }
    }

    /**
     * Print the date of  current month
     * @param jPanel Main Jpanel
     * @param date  Date
     * @param count count how many dates have been printed
     */
    private void printDate(JPanel jPanel, LocalDate date, int[] count) {
        LocalDate firstDayOfMonth = LocalDate.of(date.getYear(), date.getMonth(), 1);
        int curMonth = date.getMonthValue();
        while (curMonth == firstDayOfMonth.getMonthValue()) {
            JButton button = new JButton(String.valueOf(firstDayOfMonth.getDayOfMonth()));
            if (firstDayOfMonth.equals(LocalDate.now())) {
                button.setForeground(Color.RED);
            }
            if (firstDayOfMonth.equals(selectedDate)) {
                button.setForeground(Color.BLUE);
            }
            button.addActionListener(event -> {
                try {
                    selectedDate = LocalDate.of(date.getYear(), date.getMonth(), Integer.parseInt(button.getText()));
                    selectedButton = button;
                    queue.put(new ToDoListMessage()); // <--- adding NewGame message to the queue
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            jPanel.add(button);
            firstDayOfMonth = firstDayOfMonth.plusDays(1);
            count[0]++;
        }
    }


    /**
     * Print the date of  next month
     * @param jPanel Main Jpanel
     * @param date  Date
     * @param count count how many dates have been printed
     */
    private void printNextMonth(JPanel jPanel, LocalDate date, int[] count) {
        int i = 1;
        while (count[0] <= 35) {
            JLabel label = new JLabel(String.valueOf(i));
            label.setForeground(Color.GRAY);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            jPanel.add(label);
            count[0]++;
            i++;
        }
    }

    /**
     * Update the view
     * @param date date
     */
    public void update(LocalDate date) {
        frame.getContentPane().removeAll();
        this.date = date;
        paint();
        frame.getContentPane().revalidate();
        frame.repaint();
    }

    /**
     * paint the UI
     */
    private void paint() {
        GridBagConstraints c = new GridBagConstraints();
        JButton prevMonth = new JButton("Prev");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(prevMonth, c);

        JLabel curMonth = new JLabel(date.getMonth().getValue() + "-" + date.getDayOfMonth() + "-" + date.getYear());
        curMonth.setHorizontalAlignment(JLabel.CENTER);
        curMonth.setVerticalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        frame.add(curMonth, c);


        JButton nextMonth = new JButton("Next");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        frame.add(nextMonth, c);

        prevMonth.addActionListener(event -> {
            try {
                this.queue.put(new PrevMonthMessage()); // <--- adding NewGame message to the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        nextMonth.addActionListener(event -> {
            try {
                this.queue.put(new NextMonthMessage()); // <--- adding Hit message to the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        JPanel calPanel = new JPanel();
        calPanel.setLayout(new GridLayout(0, 7,1,1));
        int[] count = {1};
        printTitle(calPanel);
        printPrevMonth(calPanel, date, count);
        printDate(calPanel, date, count);
        printNextMonth(calPanel, date, count);
        // add everything and set layout and other standard JFrame settings
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 5;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(30,0,0,0);
        frame.add(calPanel, c);
    }

    /**
     * create todolist
     */
    public void createTodoList() { toDoListView = new ToDoListView(this); }

    /**
     *  update todolist View
     */
    public void updateTodoListView(Model model) {
        if(toDoListView == null) toDoListView = new ToDoListView(this);
        toDoListView.update(model);
    }

    /**
     *  get SelectedDate
     */
    public LocalDate getSelectedDate() {
            return this.selectedDate;
        }

    /**
     *  get Queue
     */
    public BlockingQueue<Message> getQueue() {
            return this.queue;
        }

    /**
     *  set InputStr
     */
    public void setInputStr(String str) {
            this.inputStr = str;
        }

    /**
     *  get InputStr
     */
    public String getInputStr() {
            return this.inputStr;
        }

    /**
     * get selected button
     * @return selected JButton
     */
    public JButton getSelectedButton() {
        return selectedButton;
    }

    /**
     * get frame
     * @return current frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * get current date
     * @return current date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * get todolist view
     * @return todolist view
     */
    public ToDoListView getToDoListView() {
        return toDoListView;
    }

    /**
     * Get local date
     * @return local date
     */
    public LocalDate getLocalDate() {
        return this.date;
    }
}
