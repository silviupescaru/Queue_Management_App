package org.example;

public class Task implements Comparable<Task> {
    private final int arrivalTime;
    private final int processingTime;
    private final int id;

    public Task(int arrivalTime, int processingTime, int id) {
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getId(){
        return id;
    }


    @Override
    public int compareTo(Task otherTask) {
        return Integer.compare(this.arrivalTime, otherTask.arrivalTime);
    }
}
