package controller;
import manager.SelectionPolicy;
import manager.SimulationManager;
import view.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Controller implements ActionListener {

    public GUI view;

    public Controller(GUI view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String button = e.getActionCommand();

        if(Objects.equals(button, "Start")) {

                int numberServers = 0;
                int numberClients = 0;
                int minArrivalTime = 0;
                int maxArrivalTime = 0;
                int minProcessingTime = 0;
                int maxProcessingTime = 0;
                int maxTime = 0;
                SelectionPolicy selectionPolicyObj;

                if (view.numberOfServersTextField != null) {
                    numberServers = Integer.parseInt(view.numberOfServersTextField.getText());
                }
                if (view.numberOfClientsTextField != null) {
                    numberClients = Integer.parseInt(view.numberOfClientsTextField.getText());
                }
                if (view.minArrivalTimeTextField != null) {
                    minArrivalTime = Integer.parseInt(view.minArrivalTimeTextField.getText());
                }
                if (view.maxArrivalTimeTextField != null) {
                    maxArrivalTime = Integer.parseInt(view.maxArrivalTimeTextField.getText());
                }
                if (view.minProcessingTimeTextField != null) {
                    minProcessingTime = Integer.parseInt(view.minProcessingTimeTextField.getText());
                }
                if (view.maxProcessingTimeTextField != null) {
                    maxProcessingTime = Integer.parseInt(view.maxProcessingTimeTextField.getText());
                }
                if (view.timeLimitTextField != null) {
                    maxTime = Integer.parseInt(view.timeLimitTextField.getText());
                }
                    selectionPolicyObj = SelectionPolicy.valueOf(view.selectionPolicy);

                SimulationManager simulationManager = new SimulationManager(numberServers, numberClients, minArrivalTime,
                        maxArrivalTime, minProcessingTime, maxProcessingTime, maxTime, view, selectionPolicyObj);

                view.eventLogTextArea.setText(simulationManager.getLogNow());
                Thread thread = new Thread(simulationManager);
                thread.start();
        }

        if(Objects.equals(button, "Stop")){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}

