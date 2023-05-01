package strategy;
import manager.Server;
import manager.Task;

import java.util.ArrayList;

public interface Strategy {
    void addTask(ArrayList<Server> servers, Task t);
}

