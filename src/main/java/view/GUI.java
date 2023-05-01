package view;
import controller.Controller;


import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GUI extends JFrame implements ActionListener {

    public JTextField numberOfServersTextField = new JTextField();
    public JTextField numberOfClientsTextField = new JTextField();
    public JTextField minArrivalTimeTextField = new JTextField();
    public JTextField maxArrivalTimeTextField = new JTextField();
    public JTextField minProcessingTimeTextField = new JTextField();
    public JTextField maxProcessingTimeTextField = new JTextField();
    public JTextField timeLimitTextField = new JTextField();
    public String selectionPolicy;

    public JPanel eventLogPanel;
    public JLabel eventLogLabel;
    public JTextArea eventLogTextArea;
    public JScrollPane eventLogScrollPane;
    public JPanel outputPanel;


    public GUI() {

        setTitle("Queue Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 700);

        Border border = BorderFactory.createLineBorder(new Color(66, 92, 90));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(66, 92, 90));
        inputPanel.setLayout(new GridLayout(10, 2, 5, 5));



        JLabel timeLimitLabel = new JLabel("Time Limit:");
        timeLimitLabel.setForeground(new Color(255, 195, 162));
        timeLimitLabel.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));

        timeLimitTextField.setBackground(new Color(66, 92, 90));
        timeLimitTextField.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        timeLimitTextField.setForeground(new Color(255, 195, 162));
        timeLimitTextField.setBorder(border);

        inputPanel.add(timeLimitLabel);
        inputPanel.add(timeLimitTextField);



        JLabel maxProcessingTimeLabel = new JLabel("Max Processing Time:");
        maxProcessingTimeLabel.setForeground(new Color(255, 195, 162));
        maxProcessingTimeLabel.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));

        maxProcessingTimeTextField.setBackground(new Color(66, 92, 90));
        maxProcessingTimeTextField.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        maxProcessingTimeTextField.setForeground(new Color(255, 195, 162));
        maxProcessingTimeTextField.setBorder(border);

        inputPanel.add(maxProcessingTimeLabel);
        inputPanel.add(maxProcessingTimeTextField);



        JLabel minProcessingTimeLabel = new JLabel("Min Processing Time:");
        minProcessingTimeLabel.setForeground(new Color(255, 195, 162));
        minProcessingTimeLabel.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));

        minProcessingTimeTextField.setBackground(new Color(66, 92, 90));
        minProcessingTimeTextField.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        minProcessingTimeTextField.setForeground(new Color(255, 195, 162));
        minProcessingTimeTextField.setBorder(border);

        inputPanel.add(minProcessingTimeLabel);
        inputPanel.add(minProcessingTimeTextField);



        JLabel maxArrivalTimeLabel = new JLabel("Max Arrival Time:");
        maxArrivalTimeLabel.setForeground(new Color(255, 195, 162));
        maxArrivalTimeLabel.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));

        maxArrivalTimeTextField.setBackground(new Color(66, 92, 90));
        maxArrivalTimeTextField.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        maxArrivalTimeTextField.setForeground(new Color(255, 195, 162));
        maxArrivalTimeTextField.setBorder(border);

        inputPanel.add(maxArrivalTimeLabel);
        inputPanel.add(maxArrivalTimeTextField);



        JLabel minArrivalTimeLabel = new JLabel("Min Arrival Time:");
        minArrivalTimeLabel.setForeground(new Color(255, 195, 162));
        minArrivalTimeLabel.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));

        minArrivalTimeTextField.setBackground(new Color(66, 92, 90));
        minArrivalTimeTextField.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        minArrivalTimeTextField.setForeground(new Color(255, 195, 162));
        minArrivalTimeTextField.setBorder(border);

        inputPanel.add(minArrivalTimeLabel);
        inputPanel.add(minArrivalTimeTextField);



        JLabel numberOfServersLabel = new JLabel("Number of Servers:");
        numberOfServersLabel.setForeground(new Color(255, 195, 162));
        numberOfServersLabel.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));

        numberOfServersTextField.setBackground(new Color(66, 92, 90));
        numberOfServersTextField.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        numberOfServersTextField.setForeground(new Color(255, 195, 162));
        numberOfServersTextField.setBorder(border);

        inputPanel.add(numberOfServersLabel);
        inputPanel.add(numberOfServersTextField);



        JLabel numberOfClientsLabel = new JLabel("Number of Clients:");
        numberOfClientsLabel.setForeground(new Color(255, 195, 162));
        numberOfClientsLabel.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));

        numberOfClientsTextField.setBackground(new Color(66, 92, 90));
        numberOfClientsTextField.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        numberOfClientsTextField.setForeground(new Color(255, 195, 162));
        numberOfClientsTextField.setBorder(border);

        inputPanel.add(numberOfClientsLabel);
        inputPanel.add(numberOfClientsTextField);



        JLabel selectionPolicyLabel = new JLabel("Selection Policy:");
        selectionPolicyLabel.setForeground(new Color(255, 195, 162));
        selectionPolicyLabel.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        selectionPolicyLabel.setBackground(new Color(66, 92, 90));
        inputPanel.add(selectionPolicyLabel);

        JComboBox<String> selectionPolicyComboBox = new JComboBox<>(new String[]{"SHORTEST_QUEUE", "SHORTEST_TIME"});
        selectionPolicy = (String) selectionPolicyComboBox.getSelectedItem();
        selectionPolicyComboBox.setBackground(new Color(66, 92, 90));
        selectionPolicyComboBox.setForeground(new Color(255, 195, 162));
        selectionPolicyComboBox.setFont(new Font("Ubuntu Mono", Font.BOLD, 16));
        selectionPolicyComboBox.setBorder(border);
        selectionPolicyLabel.setBorder(border);
        inputPanel.add(selectionPolicyComboBox);



        JComboBox<String> selectionPresetComboBox = new JComboBox<>(new String[]{"PRESET 1", "PRESET 2", "PRESET 3"});
        selectionPresetComboBox.setBackground(new Color(66, 92, 90));
        selectionPresetComboBox.setForeground(new Color(255, 195, 162));
        selectionPresetComboBox.setFont(new Font("Ubuntu Mono", Font.BOLD, 16));
        selectionPresetComboBox.setBorder(border);
        selectionPresetComboBox.setBorder(border);
        inputPanel.add(selectionPresetComboBox);

        JButton loadPresetButton = new JButton("Load");
        loadPresetButton.addActionListener(e -> {
            String preset = (String) selectionPresetComboBox.getSelectedItem();
            switch (Objects.requireNonNull(preset)) {
                case "PRESET 1" -> {
                    numberOfClientsTextField.setText("4");
                    numberOfServersTextField.setText("2");
                    timeLimitTextField.setText("60");
                    minArrivalTimeTextField.setText("2");
                    maxArrivalTimeTextField.setText("4");
                    minProcessingTimeTextField.setText("2");
                    maxProcessingTimeTextField.setText("30");
                    selectionPolicy = (String) selectionPolicyComboBox.getSelectedItem();
                }
                case "PRESET 2" -> {
                    numberOfClientsTextField.setText("50");
                    numberOfServersTextField.setText("5");
                    timeLimitTextField.setText("60");
                    minArrivalTimeTextField.setText("2");
                    maxArrivalTimeTextField.setText("40");
                    minProcessingTimeTextField.setText("1");
                    maxProcessingTimeTextField.setText("7");
                    selectionPolicy = (String) selectionPolicyComboBox.getSelectedItem();
                }
                case "PRESET 3" -> {
                    numberOfClientsTextField.setText("1000");
                    numberOfServersTextField.setText("20");
                    timeLimitTextField.setText("200");
                    minArrivalTimeTextField.setText("10");
                    maxArrivalTimeTextField.setText("100");
                    minProcessingTimeTextField.setText("3");
                    maxProcessingTimeTextField.setText("9");
                    selectionPolicy = (String) selectionPolicyComboBox.getSelectedItem();
                }
            }
        });
        loadPresetButton.setForeground(new Color(255, 195, 162));
        loadPresetButton.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        loadPresetButton.setBackground(new Color(66, 92, 90));
        loadPresetButton.setBorder(border);
        inputPanel.add(loadPresetButton);



        contentPanel.add(inputPanel, BorderLayout.WEST);



        outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(1, 1));



        eventLogPanel = new JPanel();
        eventLogPanel.setBackground(new Color(255, 210, 186));
        eventLogPanel.setLayout(new BoxLayout(eventLogPanel, BoxLayout.Y_AXIS));

        eventLogLabel = new JLabel();
        eventLogLabel.setBackground(new Color(66, 92, 90));
        eventLogPanel.add(eventLogLabel);

        eventLogTextArea = new JTextArea();
        eventLogTextArea.setBackground(new Color(255, 210, 186));
        eventLogTextArea.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        eventLogTextArea.setEditable(false);

        eventLogPanel.add(eventLogTextArea);
        eventLogScrollPane = new JScrollPane(eventLogTextArea);
        eventLogScrollPane.setBackground(new Color(255, 210, 186));
        eventLogPanel.add(eventLogScrollPane);
        outputPanel.add(eventLogPanel);



        contentPanel.add(outputPanel);



        numberOfClientsTextField.setText("4");
        numberOfServersTextField.setText("2");
        timeLimitTextField.setText("30");
        minArrivalTimeTextField.setText("2");
        maxArrivalTimeTextField.setText("4");
        minProcessingTimeTextField.setText("2");
        maxProcessingTimeTextField.setText("30");



        Controller controller = new Controller(this);

        JButton startButton = new JButton("Start");
        startButton.setActionCommand("Start");
        startButton.addActionListener(controller);
        startButton.setForeground(new Color(255, 195, 162));
        startButton.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        startButton.setBackground(new Color(66, 92, 90));
        startButton.setBorder(border);
        inputPanel.add(startButton);



        JButton stopButton = new JButton("Stop");
        stopButton.setActionCommand("Stop");
        stopButton.addActionListener(controller);
        stopButton.setForeground(new Color(255, 195, 162));
        stopButton.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        stopButton.setBackground(new Color(66, 92, 90));
        stopButton.setBorder(border);
        inputPanel.add(stopButton);

        setContentPane(contentPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


