package strategy;

import manager.Server;
import manager.Task;

import java.util.ArrayList;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addTask(ArrayList<Server> servers, Task task) {
        int minWaitingPeriod = servers.get(0).waitingPeriod.intValue();
        Server minServer = servers.get(0);
        for(Server server : servers) {
            if(minWaitingPeriod > server.waitingPeriod.intValue()) {
                minServer = server;
                minWaitingPeriod = server.waitingPeriod.intValue();
            }
        }
        minServer.addTask(task);
    }
}