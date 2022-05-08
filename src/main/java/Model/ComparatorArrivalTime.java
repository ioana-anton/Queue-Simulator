package Model;

import java.util.Comparator;

public class ComparatorArrivalTime implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        if(o1.compareTo(o2)==0)
            return 0;
        return -o1.compareTo(o2);
    }
}
