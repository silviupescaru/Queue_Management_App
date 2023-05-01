package manager;
import view.GUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SimulationManager implements Runnable {

    int timeLimit;
    int maxProcessingTime;
    int minProcessingTime;
    int maxArrivalTime;
    int minArrivalTime;
    int numberOfServers;
    int numberOfClients;

    String logOfEvents;
    SelectionPolicy selectionPolicy;

    GUI view;
    Scheduler scheduler;
    ArrayList<Task> generatedTasks;

    public SimulationManager(int numberOfServers, int numberOfClients, int minArrivalTime, int maxArrivalTime,
                             int minProcessingTime, int maxProcessingTime, int timeLimit, GUI view, SelectionPolicy selectionPolicy) {
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.timeLimit = timeLimit;
        this.view = view;
        this.selectionPolicy = selectionPolicy;
        this.logOfEvents = "";
        this.generatedTasks = null;
        this.scheduler = new Scheduler(numberOfServers, selectionPolicy);
        generateNRandomTasks();
    }

    void generateNRandomTasks() {

        generatedTasks = new ArrayList<>();
        int totalWaitingTime = 0;
        int totalProcessingTime = 0;
        for(int i = 0; i < numberOfClients; i++) {
            Task task = new Task();
            task.id = i + 1;
            task.arrivalTime = minArrivalTime + (int) (Math.random() * (maxArrivalTime - minArrivalTime));
            task.processingTime = minProcessingTime + (int) (Math.random() * (maxProcessingTime - minProcessingTime));
            generatedTasks.add(task);
        }
        Collections.sort(generatedTasks);
    }

    int allQueuesFinished() {
        if(!generatedTasks.isEmpty())
            return 0;
        for(Server server : scheduler.servers) {
            if(server.tasks.size() != 0) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    public void run() {
        int timeNow = 0, totalWaitingPeriodNow, maxWaitingPeriod = 0;
        scheduler.runThreads();
        logOfEvents = logOfEvents + selectionPolicy.name() + "\n";
        while(timeNow < timeLimit && allQueuesFinished() != 1) {
            timeNow++;
            String timeInfo = "\n Time: " + timeNow + "\n";
            ArrayList<Task> newGeneratedTasks = new ArrayList<>();
            for(Task task : generatedTasks) {
                if(task.arrivalTime == timeNow) {
                    task.processingTime++;
                    scheduler.dispatchTask(task);
                }
                else{
                    newGeneratedTasks.add(task);
                }
                generatedTasks = newGeneratedTasks;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            logOfEvents = logOfEvents + timeInfo + getLogNow();

            view.eventLogTextArea.setText(logOfEvents);
        }
        logOfEvents = logOfEvents + "\nEnd of Simulation";
        view.eventLogTextArea.setText(logOfEvents);

        writeToTextFile(logOfEvents);
    }

    public String getLogNow() {

        StringBuilder log = new StringBuilder();
        log.append("Waiting Clients:\n");
        for(Task task : generatedTasks) {
            log.append(task.toString()).append(" ");
        }
        log.append("\n");
        int queueNumber = 1;
        for(Server server : scheduler.servers) {
            log.append("Queue ").append(queueNumber).append(": ");
            if(server.toString().isEmpty()) {
                log.append("closed");
            }
            else {
                log.append(server);
            }
            queueNumber++;
            log.append("\n");
        }
        log.append("\n");
        return log.toString();
    }

    void writeToTextFile(String logOfEvents) {
        String fileName = "SimulationLog.txt";
        File file = new File(fileName);
        int fileNumber = 1;
        while (file.exists()) {
            fileName = "SimulationLog" + fileNumber + ".txt";
            file = new File(fileName);
            fileNumber++;
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(logOfEvents);
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}

