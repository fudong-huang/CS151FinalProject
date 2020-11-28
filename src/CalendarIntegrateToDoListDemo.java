import controller.Controller;
import model.Message;
import model.Model;
import model.ToDoList;
import view.View;

import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class CalendarIntegrateToDoListDemo {

    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static View view;
    private static Model model;
    public static void main(String[] args) throws InterruptedException {
        model = new Model(new ToDoList());
        view = View.init(queue, LocalDate.now());
        Controller controller = new Controller(view, model, queue);
        controller.mainLoop();
        view.dispose();
        queue.clear();
    }
}