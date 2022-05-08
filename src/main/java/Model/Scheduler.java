package Model;

import Controller.SimulationManager;

import java.security.Policy;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class Scheduler{
    private ArrayList<Server> servers=new ArrayList<>();
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer, SimulationManager manager){
        this.maxNoServers=maxNoServers;
        this.maxTasksPerServer=maxTasksPerServer;
        //for maxNoSevers
        //- create server object
        //- create thread with the object
        changeStrategy(SelectionPolicy.SHORTEST_TIME);
        int j=0;
        for(int i=0;i<maxNoServers;i++){
            Server s=new Server(maxTasksPerServer,manager,j+1);
            servers.add(s);
            j++;
            Thread t=new Thread(s);
            t.start();
        }
        System.out.println("Numar de servere create: "+servers.size());

    }

    public void changeStrategy(SelectionPolicy policy){
    //apply strategy patter to instantiate the strategy with the concrete strategy corresponding to policy
        if(policy==SelectionPolicy.SHORTEST_QUEUE){
            strategy= new ConcreteStrategyQueue();
        }
        if(policy==SelectionPolicy.SHORTEST_TIME){
            strategy=new ConcreteStrategyTime();
        }
    }

    public void printServers(){
        for(Server it:servers){
            System.out.printf("Server %d: ",it.getIdServer());
           it.printServer();
            System.out.println();
        }
    }

    public void dispatchTask(Task t){
        //call the strategy addTask method
        System.out.printf("(Adding task - "+t.getID()+"; ");
        strategy.addTask(servers,t);
    }

    public List<Server> getServers(){
        return servers;
    }

}
