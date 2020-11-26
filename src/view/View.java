package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;

public class View extends JFrame{
    private JFrame frame;
    private BlockingQueue<Message> queue;
    private JPanel panel;
    private LocalDate date = LocalDate.of(2020, 9, 5);
    // count for the calendar button;


    public static View init(BlockingQueue<Message> queue) {
        // Create object of type view
        return new View(queue);
    }

    private View(BlockingQueue<Message> queue) {
        this.queue = queue;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton prevMonth = new JButton("Prev");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(prevMonth, c);

        JButton curMonth = new JButton(date.getMonth().getValue() + "-" + date.getDayOfMonth() + "-" + date.getYear());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        add(curMonth, c);


        JButton nextMonth = new JButton("Next");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        add(nextMonth, c);

        JPanel calPanel = new JPanel();
        calPanel.setLayout(new GridLayout(0, 7,1,1));
        int[] count = {1};
        printTitle(calPanel);
        printPrevMonth(calPanel, date, count);
        printDate(calPanel, date, count);
        printNextMonth(calPanel, date, count);


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
        add(calPanel, c);
        pack();
        setTitle("Calendar&ToDoList");
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void change() {
        // TODO: do all the updates and repaint
        //gameFrame.repaint();
    }

    public void dispose() {
        // TODO: clear all the resources
        // for example, gameFrame.dispose();
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


    private void printDate(JPanel jPanel, LocalDate datem, int[] count) {
        LocalDate firstDayOfMonth = LocalDate.of(date.getYear(), date.getMonth(), 1);
        int curMonth = date.getMonthValue();
        while (curMonth == firstDayOfMonth.getMonthValue()) {
            JButton button = new JButton(String.valueOf(firstDayOfMonth.getDayOfMonth()));
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
}
