package manager;

public class Task implements Comparable<Task> {
    int id;
    int arrivalTime;
    int processingTime;

    public Task() {
        id = 0;
        arrivalTime = 0;
        processingTime = 0;
    }

    @Override
    public int compareTo(Task task) {
        return arrivalTime - task.arrivalTime;
    }

    @Override
    public String toString() {
        return "(" + id + ", " + arrivalTime + ", " + processingTime + ")";
    }
}

