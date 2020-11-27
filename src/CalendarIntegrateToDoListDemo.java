import controller.Controller;
import model.Message;
import model.Model;
import model.ToDoList;
import view.ToDoListView;
import view.View;

import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class CalendarIntegrateToDoListDemo {

    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static View view;
    private static Model model;
    public static void main(String[] args) throws InterruptedException {
        view = View.init(queue, LocalDate.now());
        model = new Model(new ToDoList());
        Controller controller = new Controller(view, model, queue);
        controller.mainLoop();
        System.out.println(123);
        view.dispose();
        queue.clear();
    }
}