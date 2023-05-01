package manager;
import strategy.ConcreteStrategyQueue;
import strategy.ConcreteStrategyTime;
import strategy.Strategy;

import java.util.ArrayList;

public class Scheduler {

    ArrayList<Server> servers;
    ArrayList<Thread> threads;
    Strategy strategy;

    public Scheduler(int numberOfServers, SelectionPolicy selectionPolicy) {

        servers = new ArrayList<>();
        threads = new ArrayList<>();
        if(selectionPolicy.name().equals("SHORTEST_QUEUE"))
            strategy = new ConcreteStrategyQueue();
        else
            strategy = new ConcreteStrategyTime();
        for(int i = 0; i < numberOfServers; i++) {
            Server server = new Server();
            servers.add(server);
            threads.add(new Thread(server));
        }
    }

    void dispatchTask(Task t) {
        strategy.addTask(servers, t);
    }

    void runThreads() {
        for(Thread thread : threads) {
            thread.start();
        }
    }
}

