package Model;

public class Task {

    private int ID=0;
    private int arrivalTime;
    private int finishTime;
    private int processingPeriod;
    private int counter=0;

    public void incrementCounter(){counter++;}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getProcessingPeriod() {
        return processingPeriod;
    }

    public void setProcessingPeriod(int processingPeriod) {
        this.processingPeriod = processingPeriod;
    }
    public void decreaseProcessingPeriod(){processingPeriod--;}

    int compareTo(Task p) {
        Integer x = (Integer) p.arrivalTime;
        return x.compareTo((Integer) arrivalTime);
    }
}
