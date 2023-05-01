package org.example;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        Server selectedServer = servers.get(0);
        for (Server server : servers) {
            if (server.getTasks().length < selectedServer.getTasks().length) {
                selectedServer = server;
            }
        }
        selectedServer.addTask(t);
    }
}