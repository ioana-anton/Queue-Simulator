package Controller;

import Controller.Controller;
import View.*;
import Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.System.exit;

public class SimulationManager implements Runnable {

    public int timeLimit; //timpul de simulare
    public int maxProcessingTime; //min service time
    public int minProcessingTime;   //max
    public int maxArrivalTime; //min service time
    public int minArrivalTime;   //max
    public int numberOfServers; //nr cozi
    public int numberOfClients; //nr clienti
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME; //tot timpul cautam timpul de asteptare min

    private SimulationFrame frame; //fereastra de simulare
    private Scheduler scheduler; //asociaza clientii la o coada
    private ArrayList<Task> generatedTasks = new ArrayList<>(); //clienti generati aleatoriu

    private ArrayList<Data> date=new ArrayList<>();
    private ArrayList<PeakHour> totalWaitingTime=new ArrayList<>();
    private ArrayList<Integer> serviceTime=new ArrayList<>();

    private Integer totalWaitTime=0;

    private CyclicBarrier barrier;

    public SimulationManager(int timeLimitP, int maxPTime, int minPTime, int noS, int noC, int maxATime, int minATime) {

        timeLimit = timeLimitP;
        maxProcessingTime = maxPTime;
        minProcessingTime = minPTime;
        maxArrivalTime = maxATime;
        minArrivalTime = minATime;
        numberOfServers = noS;
        numberOfClients = noC;
        generateNRandomTasks();
        //BlockingQueue<Task> blockingQueueTask =new ArrayBlockingQueue<>(noC,false,generatedTasks);
        scheduler = new Scheduler(numberOfServers, 1000, this);
        scheduler.changeStrategy(selectionPolicy);
        //frame=new SimulationFrame(numberOfServers,numberOfClients,scheduler.getServers(),0);
        //frame.setVisible(true);

    }

    private boolean checkAppearance(int arrivalTime) {

        if (generatedTasks.isEmpty() == false)
            for (Task it : generatedTasks) {
                if (it.getArrivalTime() == arrivalTime)
                    return true;
            }

        return false;
    }

    private void generateNRandomTasks() {
        for (int i = 0; i < numberOfClients; i++) {
            Task t = new Task();
            int processTime = (int) Math.floor(Math.random() * (maxProcessingTime - minProcessingTime + 1) + minProcessingTime);
            int arrivalTime = (int) Math.floor(Math.random() * (maxArrivalTime - minArrivalTime + 1) + minArrivalTime) - 1;
           // while (checkAppearance(arrivalTime))
             //   arrivalTime = (int) Math.floor(Math.random() * (maxArrivalTime - minArrivalTime + 1) + minArrivalTime) - 1;
            t.setProcessingPeriod(processTime);
            t.setArrivalTime(arrivalTime);
            t.setFinishTime(processTime + arrivalTime);
            generatedTasks.add(t);
            //System.out.println("Clientul "+i+" a fost generat.");
        }
        ComparatorArrivalTime c = new ComparatorArrivalTime();
        generatedTasks.sort(c);
        System.out.println("S-au generat: " + generatedTasks.size() + " clienti");
        int i = 1;
        for (Task it : generatedTasks) {
            it.setID(i);
            i++;
            System.out.println("ID: " + it.getID() + " | Timp sosire: " + it.getArrivalTime() + " | Timp procesare: " + it.getProcessingPeriod());
        }
    }

    public void awaitBarrier() throws BrokenBarrierException, InterruptedException {
        barrier.await();
    }

    private double calculateAverageWaitingTime(){
        //Data t;
        double waitTime=0;
        for(Data it:date){
            waitTime+=it.getWaitingTime();
        }
        return waitTime/date.size();
    }
    private double calculateAverageServiceTime(){
        double rez=0;
        for(Integer it:serviceTime){
            rez+=it;
        }
        return rez/serviceTime.size();
    }

    private int findPeekHour(){
        int timpMax=0;
        int waitingTime=0;
        for(PeakHour it:totalWaitingTime){
            if(it.getTotalWaitingTime()>timpMax){
                waitingTime=it.getTotalWaitingTime();
                timpMax=it.getCurrentTime();
            }
        }
        return timpMax;
    }

    @Override
    public void run() {
        int currentTime = 0;
        while ((currentTime <= timeLimit) && (generatedTasks.isEmpty()==false)) {
            try {
                barrier = new CyclicBarrier(scheduler.getServers().size() + 1);

                System.out.println("Timp " + currentTime);
                System.out.println("Waiting line: ");

                Iterator<Task> itr = generatedTasks.iterator();
                while (itr.hasNext()) {
                    Task it = itr.next();
                    System.out.printf( it.getID()+" ");
                    if (it.getArrivalTime() == currentTime) {
                        serviceTime.add(it.getProcessingPeriod());
                        scheduler.dispatchTask(it);
                        itr.remove();
                    }
                }
                System.out.println();
                int i = 1;
                totalWaitTime=0;
                for (Server it : scheduler.getServers()) {
                    System.out.printf("Time remaining for server %d: %d\n", i, it.getWaitingPeriod().intValue());
                    totalWaitTime+=it.getWaitingPeriod().intValue();
                    it.removeFinishedTasks();
                    i++;
                }
               // scheduler.printServers();
                totalWaitingTime.add(new PeakHour(totalWaitTime,currentTime));
                i = 1;
                for (Server it : scheduler.getServers()) {
                    if (it.getWaitingPeriod().intValue() > 0)
                        it.getWaitingPeriod().getAndDecrement();
                    date.add(new Data(it.getWaitingPeriod().intValue(),i,currentTime));
                    i++;
                }

                /*
                //update UI Frame
                frame=new SimulationFrame(numberOfServers,numberOfClients,scheduler.getServers(),currentTime);
                frame.revalidate();
                frame.repaint();
                */

                //Move on
                currentTime++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double x=calculateAverageWaitingTime();
        double z=calculateAverageServiceTime();
        int y=findPeekHour();

        System.out.println("Average waiting time "+x);
        System.out.println("Average service time "+z);
        System.out.println("Peak hour: "+y);

        exit(0);
    }


    public static void main(String[] args) {


        InputFrame homeF = new InputFrame();

        /*
        PrintStream fileOut= null;
        try {
            fileOut = new PrintStream("./log.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(fileOut);*/
        Controller c = new Controller(homeF);
        homeF.setVisible(true);
        //SimulationFrame s=new SimulationFrame(2,4);
        //s.setVisible(true);

    }
}
