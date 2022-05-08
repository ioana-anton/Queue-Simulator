package Model;

public class DataDouble {
    private double waitingTime;

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getServerNumber() {
        return serverNumber;
    }

    public void setServerNumber(int serverNumber) {
        this.serverNumber = serverNumber;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    private int serverNumber;
    private int currentTime;

    public DataDouble(double waitingTime, int serverNumber, int currentTime) {
        this.waitingTime = waitingTime;
        this.serverNumber = serverNumber;
        this.currentTime = currentTime;
    }
}
