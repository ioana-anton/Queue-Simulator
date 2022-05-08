package View;

import Model.Server;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationFrame extends JFrame {


    private int currentTime = 0;
    private List<Server> servers;
    private JPanel mainPanel = new JPanel();

    public SimulationFrame(int numberOfServers, int numberOfClients, List<Server> s, int timeC) {

        servers = s;
        currentTime = timeC;

        JPanel[] serverPanels = new JPanel[numberOfServers];
        JLabel[][] clientIcons = new JLabel[numberOfServers][numberOfClients];
        JLabel time = new JLabel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        int mainPanelWidth = 90 * numberOfClients + 5 * (numberOfClients - 1);
        mainPanel.setPreferredSize(new Dimension(mainPanelWidth, 150 * numberOfServers));
        System.out.println(150 * numberOfServers);

        mainPanel.add(Box.createRigidArea(new Dimension(mainPanelWidth, 10)));
        time.setText(String.valueOf(currentTime));
        time.setPreferredSize(new Dimension(30, 10));
        mainPanel.add(time);

        int serverPanelHeight = (int) Math.floor((150 * numberOfServers - 40 - 10 * (numberOfServers - 1)) / numberOfServers);
        System.out.println(serverPanelHeight);

        int widthClientIcon = (int) Math.floor((mainPanelWidth - 5 * (numberOfClients - 1)) / numberOfClients);
        System.out.println(widthClientIcon);

        for (int i = 0; i < numberOfServers; i++) {
            serverPanels[i] = new JPanel();
            // serverPanels[i].setBackground(Color.magenta);
            serverPanels[i].setPreferredSize(new Dimension(mainPanelWidth, serverPanelHeight));
        }

        for (int i = 0; i < numberOfServers; i++) {
            for (int j = 0; j < numberOfClients; j++) {
                ImageIcon client = new ImageIcon("client.png");
                clientIcons[i][j] = new JLabel(client);
                clientIcons[i][j].setPreferredSize(new Dimension(widthClientIcon, serverPanelHeight));
            }
        }

        for (int i = 0; i < numberOfServers; i++) {
            for (int j = 0; j < numberOfClients; j++) {
                serverPanels[i].add(clientIcons[i][j]);
                serverPanels[i].add(Box.createRigidArea(new Dimension(5, serverPanelHeight)));
            }
            mainPanel.add(serverPanels[i]);
            mainPanel.add(Box.createRigidArea(new Dimension(mainPanelWidth, 10)));
        }

        mainPanel.add(Box.createRigidArea(new Dimension(mainPanelWidth, 20)));

        this.setContentPane(mainPanel);
        this.pack();
        this.setTitle("Simulation Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

}
