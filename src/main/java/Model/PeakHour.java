package Model;

public class PeakHour {
    private int totalWaitingTime;
    private int currentTime;

    public PeakHour(int totalWaitingTime, int currentTime) {
        this.totalWaitingTime = totalWaitingTime;
        this.currentTime = currentTime;
    }

    public int getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public void setTotalWaitingTime(int totalWaitingTime) {
        this.totalWaitingTime = totalWaitingTime;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }
}
