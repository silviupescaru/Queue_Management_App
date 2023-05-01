package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final BlockingQueue<Task> tasks;
    private final AtomicInteger waitingPeriod;

    public Server() {
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.getAndAdd(newTask.getProcessingTime());
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }


    public void run() {
        while (true) {
            try {
                Task currentTask = tasks.take();
                Thread.sleep(currentTask.getProcessingTime() * 1000);
                waitingPeriod.getAndAdd(-currentTask.getProcessingTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Task[] getTasks() {
        return tasks.toArray(new Task[tasks.size()]);
    }
}