package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class View extends JFrame{
    private JFrame frame;
    private BlockingQueue<Message> queue;
    private LocalDate date;
    private LocalDate selectedDate;
    private ToDoListView toDoListView;
    private String inputStr;
    private ArrayList<String> frontColorList;
    private JButton selectedButton;

    public static View init(BlockingQueue<Message> queue,LocalDate date) {
        // Create object of type view
        return new View(queue, date);
    }

    private View(BlockingQueue<Message> queue, LocalDate date) {
        this.date = date;
        this.queue = queue;
        this.frame = new JFrame();
        //toDoListView.init(this);
        frame.setLayout(new GridBagLayout());
        frontColorList = new ArrayList<>();
        selectedDate = null;
        selectedButton = null;
        paint();
        frame.pack();
        frame.setTitle("Calendar&ToDoList");
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void printTitle(JPanel calPanel) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            String str = dayOfWeek.toString();
            JLabel label = new JLabel(str);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            calPanel.add(label);
        }
    }

    private void printPrevMonth(JPanel jPanel, LocalDate date, int[] count) {
        LocalDate firstDayOfMonth = LocalDate.of(date.getYear(), date.getMonth(), 1);
        int firstDay = firstDayOfMonth.getDayOfWeek().getValue();
        if (firstDay != 7) {
            System.out.println(firstDay);
            for (int i = 0; i < firstDay; i++) {
                firstDayOfMonth = firstDayOfMonth.minusDays(1);
            }

            for(int i = 0; i < firstDay; i++) {
                JLabel label = new JLabel(Integer.toString(firstDayOfMonth.getDayOfMonth()));
                label.setForeground(Color.GRAY);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                jPanel.add(label);
                firstDayOfMonth = firstDayOfMonth.plusDays(1);
                count[0]++;
            }
        }
    }


    public JButton getSelectedButton() {
        return selectedButton;
    }

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

    public LocalDate getLocalDate() {
        return this.date;
    }

    public void update(LocalDate date) {
        frame.getContentPane().removeAll();
        this.date = date;
        paint();
        System.out.println("calling repaint");
        frame.getContentPane().revalidate();
        frame.repaint();
    }

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
        //c.weighty = 5;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(30,0,0,0);
        frame.add(calPanel, c);
    }
        public void createTodoList() {
            toDoListView = new ToDoListView(this);
        }

        public void updateTodoListView(Model model) {
            if(toDoListView == null) toDoListView = new ToDoListView(this);
            toDoListView.update(model);
        }
        public LocalDate getSelectedDate() {
            return this.selectedDate;
        }

        public BlockingQueue<Message> getQueue() {
            return this.queue;
        }

        public void setInputStr(String str) {
            this.inputStr = str;
        }

        public String getInputStr() {
            return this.inputStr;
        }
}
