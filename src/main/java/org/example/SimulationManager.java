package org.example;

public class SimulationManager implements Runnable
{
    //data read from ui
    public int timeLimit = 100;
    public int maxProcessingTime = 10;
    public int minProcessingTime = 2;
    public int numberOfServers = 3;
    public int numberOfClients = 100;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks;

    public SimulationManager()
    {

    }

    private void generateNRandomTasks()
    {

    }

    @Override
    public void run()
    {
        int currentTime = 0;
        while (currentTime < timeLimit)
        {
            currentTime++;
        }
    }

    public static void main(String[] args)
    {
        SimulationManager gen = new SimulationManager();
        Thread t = new Thread(gen);
        t.start();
    }
}