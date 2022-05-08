package Model;

import Controller.SimulationManager;
import View.SimulationFrame;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Server implements Runnable {
    private BlockingQueue<Task> tasks;

    private AtomicInteger waitingPeriod = new AtomicInteger(0);

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    private SimulationManager man;

    private CountDownLatch latch;

    public int getIdServer() {
        return idServer;
    }

    private int idServer;

    /*
    public void calculateWaitingPeriod() {
        waitingPeriod = new AtomicInteger(0);
        for (Task it : tasks)
            waitingPeriod.getAndAdd(it.getProcessingPeriod());
    }
    */

    public Server(int size, SimulationManager manager,int idS) {
        man = manager;
        //System.out.println("Numar maxim de clienti per server: " + size);
        tasks = new ArrayBlockingQueue<>(size, false);
        idServer=idS;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.getAndAdd(newTask.getProcessingPeriod());
    }

    public void removeFinishedTasks(){
        for(Task it:tasks)
            if(it.getProcessingPeriod()==0)
                tasks.remove(it);
    }

    public void printServer(){
        for(Task it:tasks)
            System.out.printf("%d ",it.getID());
    }

    @Override
    public void run() {
        while (true) {
            try {

                Task t = tasks.take();

                t.decreaseProcessingPeriod();
                man.awaitBarrier();

                //for(Task it:tasks)
                //System.out.println();

                return;
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    public Task[] getTasks() {
        System.out.println();
        Task[] tasksArray = new Task[tasks.size()];
        tasksArray = tasks.toArray(tasksArray);
        return tasksArray;
    }

    public BlockingQueue<Task> getTasksB() {
        return tasks;
    }

}
