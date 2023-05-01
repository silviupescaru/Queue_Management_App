package org.example;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        Server selectedServer = servers.get(0);
        for (Server server : servers) {
            if (server.getWaitingPeriod().intValue() < selectedServer.getWaitingPeriod().intValue()) {
                selectedServer = server;
            }
        }
        selectedServer.addTask(t);
    }
}