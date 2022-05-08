package Model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        int timpMin = 10000;
        int ok = 0;
        if (servers.isEmpty() == false){
            for (Server it : servers) {
                if (it.getWaitingPeriod().intValue() < timpMin) {
                    timpMin = it.getWaitingPeriod().intValue();
                }
            }

            int j=0;
            for (Server it : servers) {
                if ((it.getWaitingPeriod().intValue() == timpMin) && (ok == 0)) {
                  // System.out.printf("To server %d with current waiting period: %d\n",j, it.getWaitingPeriod().intValue());
                    it.addTask(t);
                    System.out.println("Timp minim: " + timpMin+") to server "+it.getIdServer());
                    ok = 1;
                    //break;
                }
                j++;
            }
        }
    }

}

