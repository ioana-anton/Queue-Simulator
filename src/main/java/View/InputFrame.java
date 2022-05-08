package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputFrame extends JFrame {

    private JPanel mainPanel = new JPanel();

    private JTextField noClients = new JTextField();
    private JTextField noQueues = new JTextField();
    private JTextField simulationInterval = new JTextField();
    private JTextField minArrivalTime = new JTextField();
    private JTextField maxArrivalTime = new JTextField();
    private JTextField minServiceTime = new JTextField();
    private JTextField maxServiceTime = new JTextField();

    private JLabel noClientsT = new JLabel("Number of clients: ");
    private JLabel noQueuesT = new JLabel("Number of queues: ");
    private JLabel simulationIntervalT = new JLabel("Simulation Interval: ");
    private JLabel minArrivalTimeT = new JLabel("Min Arrival Time: ");
    private JLabel maxArrivalTimeT = new JLabel("Max Arrival Time: ");
    private JLabel minServiceTimeT = new JLabel("Min Service Time: ");
    private JLabel maxServiceTimeT = new JLabel("Max Service Time: ");

    private JButton confirm = new JButton("Confirm");

    public InputFrame() {
        //FlowLayout Panels for each row in the UI
        JPanel noClientsP = new JPanel();
        JPanel noQueuesP = new JPanel();
        JPanel simulationIntervalP = new JPanel();
        JPanel timeP = new JPanel();

        //Two BoxLayout Panels for min/max ArrivalTime and ServiceTime
        JPanel box1P = new JPanel();
        JPanel box2P = new JPanel();

        //Two FlowLayout Panels for each BoxLayout Panel
        JPanel row1P1 = new JPanel();
        JPanel row2P1 = new JPanel();
        JPanel row1P2 = new JPanel();
        JPanel row2P2 = new JPanel();

        //set Layout
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        noClientsP.setLayout(new FlowLayout());
        noQueuesP.setLayout(new FlowLayout());
        simulationIntervalP.setLayout(new FlowLayout());
        timeP.setLayout(new FlowLayout());

        box1P.setLayout(new BoxLayout(box1P, BoxLayout.Y_AXIS));
        box2P.setLayout(new BoxLayout(box2P, BoxLayout.Y_AXIS));

        row1P1.setLayout(new FlowLayout());
        row2P1.setLayout(new FlowLayout());
        row1P2.setLayout(new FlowLayout());
        row2P2.setLayout(new FlowLayout());

        //Panel dim
        mainPanel.setPreferredSize(new Dimension(500, 379));
        noClientsP.setPreferredSize(new Dimension(300, 22));
        noQueuesP.setPreferredSize(new Dimension(300, 22));
        confirm.setPreferredSize(new Dimension(28, 22));
        timeP.setPreferredSize(new Dimension(300, 44));

        //TextFields dim
        noClients.setPreferredSize(new Dimension(100, 22));
        noQueues.setPreferredSize(new Dimension(100, 22));
        simulationInterval.setPreferredSize(new Dimension(100, 22));
        minArrivalTime.setPreferredSize(new Dimension(30, 22));
        maxArrivalTime.setPreferredSize(new Dimension(30, 22));
        minServiceTime.setPreferredSize(new Dimension(30, 22));
        maxServiceTime.setPreferredSize(new Dimension(30, 22));


        //add TextFields and Labels to Panels
        noClientsP.add(noClientsT);
        noClientsP.add(noClients);
        noQueuesP.add(noQueuesT);
        noQueuesP.add(noQueues);
        simulationIntervalP.add(simulationIntervalT);
        simulationIntervalP.add(simulationInterval);

        row1P1.add(minArrivalTimeT);
        row1P1.add(minArrivalTime);
        row2P1.add(maxArrivalTimeT);
        row2P1.add(maxArrivalTime);

        row1P2.add(minServiceTimeT);
        row1P2.add(minServiceTime);
        row2P2.add(maxServiceTimeT);
        row2P2.add(maxServiceTime);

        box1P.add(row1P1);
        box1P.add(row2P1);
        box2P.add(row1P2);
        box2P.add(row2P2);

        timeP.add(box2P);
        timeP.add(box1P);

        // Add Panels to the mainPanel

        mainPanel.add(Box.createRigidArea(new Dimension(500, 10)));
        mainPanel.add(noClientsP);
        mainPanel.add(noQueuesP);
        mainPanel.add(simulationIntervalP);
        mainPanel.add(timeP);
        mainPanel.add(confirm);
        mainPanel.add(Box.createRigidArea(new Dimension(500, 10)));

        this.setContentPane(mainPanel);
        this.pack();
        this.setTitle("InputFrame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public String getNoClients() {
        return noClients.getText();
    }

    public String getNoQueues() {
        return noQueues.getText();
    }

    public String getSimulationInterval() {
        return simulationInterval.getText();
    }

    public String getMinArrivalTime() {
        return minArrivalTime.getText();
    }

    public String getMaxArrivalTime() {
        return maxArrivalTime.getText();
    }

    public String getMinServiceTime() {
        return minServiceTime.getText();
    }

    public String getMaxServiceTime() {
        return maxServiceTime.getText();
    }

    public void addConfirmListener(ActionListener e) {
        confirm.addActionListener(e);
    }

}
