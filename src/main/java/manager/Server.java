package manager;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

    public BlockingQueue<Task> tasks;
    public AtomicInteger waitingPeriod;

    public Server() {
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask) {
        try{
            tasks.put(newTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitingPeriod.set(waitingPeriod.intValue() + newTask.processingTime);
    }

    public Task getTasks(Task newTask) {
        return newTask;
    }

    @Override
    public void run(){

        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!tasks.isEmpty()) {
                tasks.peek().processingTime--;
                waitingPeriod.set(waitingPeriod.decrementAndGet());
                if(tasks.element().processingTime == 0) {
                    try {
                        tasks.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(Task task : tasks)
            string.append(task.toString()).append(" ");
        return string.toString();
    }
}

