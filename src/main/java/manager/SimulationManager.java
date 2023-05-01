package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationManager implements Runnable {
    // data read from ui
    public int timeLimit = 60;
    public int maxProcessingTime = 4;
    public int minProcessingTime = 2;
    public int maxArrivalTime = 30;
    public int minArrivalTime = 2;
    public int numberOfServers = 5;
    public int numberOfClients = 30;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;

    // entity responsible with queue management and client distribution
    private final Scheduler scheduler;

    // frame for displaying simulation
    private final SimulationFrame frame;

    // pool of tasks (client shopping in the store)
    private final List<Task> generatedTasks;

    public SimulationManager() {
        // initialize the scheduler
        scheduler = new Scheduler(numberOfServers, maxProcessingTime);
        scheduler.changeStrategy(selectionPolicy);

        // initialize frame to display simulation
        frame = new SimulationFrame(numberOfServers);

        // generate numberOfClients clients using generateNRandomTasks()
        // and store them to generatedTasks
        generatedTasks = new ArrayList<>();
        generateNRandomTasks();
    }

    private void generateNRandomTasks() {
        // generate N random tasks:
        // -random processing time
        // minProcessingTime < processingTime < maxProcessingTime
        // -random arrivalTime
        // sort list with respect to arrivalTime
        for (int i = 0; i < numberOfClients; i++) {
            int processingTime = (int) (Math.random() * (maxProcessingTime - minProcessingTime)) + minProcessingTime;
            int arrivalTime = (int) (Math.random() * timeLimit);
            Task task = new Task(arrivalTime, processingTime, i + 1);
            generatedTasks.add(task);
        }
        Collections.sort(generatedTasks);
    }

    @Override
    public void run() {
        int currentTime = 0;
        while (currentTime < timeLimit) {
            // iterate generatedTasks list and pick tasks that have the
            // arrivalTime equal with the currentTime
            // -send task to queue by calling the dispatchTask method
            // from Scheduler
            // -delete client from list
            for (Task task : generatedTasks) {
                if (task.getArrivalTime() == currentTime) {
                    scheduler.dispatchTask(task);
                } else if (task.getArrivalTime() > currentTime) {
                    break;
                }
            }
            int finalCurrentTime = currentTime;
            generatedTasks.removeIf(task -> task.getArrivalTime() <= finalCurrentTime);

            // update UI frame
            frame.update(scheduler.getServers());
            currentTime++;

            // wait and interval of 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // display simulation statistics
        //frame.displayStatistics(generatedTasks);
    }

    public static void main(String[] args) {
        SimulationManager gen = new SimulationManager();
        Thread t = new Thread(gen);
        t.start();
    }
}
