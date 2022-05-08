package Controller;

import View.InputFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private InputFrame homePage;
    //private SimulationManager gen;

        public Controller(InputFrame f){
            homePage=f;
            homePage.addConfirmListener(new ConfirmListener());
        }


    class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


            int timeLimit = Integer.parseInt(homePage.getSimulationInterval());
            int minProcessingTime = Integer.parseInt(homePage.getMinServiceTime());
            int maxProcessingTime = Integer.parseInt(homePage.getMaxServiceTime());
            int numberOfClients = Integer.parseInt(homePage.getNoClients());
            int numberOfServers = Integer.parseInt(homePage.getNoQueues());
            int minArrivalTime=Integer.parseInt(homePage.getMinArrivalTime());
            int maxArrivalTime=Integer.parseInt(homePage.getMaxArrivalTime());


            /*
            int timeLimit = 60;
            int minProcessingTime = 2;
            int maxProcessingTime = 4;
            int numberOfClients = 4;
            int numberOfServers = 2;
            int minArrivalTime=2;
            int maxArrivalTime=10;*/

            System.out.println("Time limit " + timeLimit);
            System.out.println("minP " + minProcessingTime);
            System.out.println("maxP " + maxProcessingTime);
            System.out.println("minA " + minArrivalTime);
            System.out.println("maxA " + maxArrivalTime);
            System.out.println("clients " + numberOfClients);
            System.out.println("servers " + numberOfServers);

            SimulationManager gen = new SimulationManager(timeLimit,maxProcessingTime,minProcessingTime,numberOfServers,numberOfClients,maxArrivalTime,minArrivalTime);
            Thread t = new Thread(gen);
            t.start();
        }
    }
}
