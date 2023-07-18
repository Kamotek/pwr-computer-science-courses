import java.io.Serializable;

public class AccessData implements Serializable, Cloneable{
    private boolean isRealTime;
    private int deadline;
    private int numberOfRequests;


    public AccessData(boolean isRealTime, int deadline) {
        this.isRealTime = isRealTime;
        this.deadline = deadline;
        this.numberOfRequests = 0;
    }

    public AccessData(){
        this.isRealTime = false;
        this.deadline = 0;
        this.numberOfRequests = 0;
    }


    public AccessData clone() throws CloneNotSupportedException{
        AccessData clone = (AccessData) super.clone();
        return clone;
    }

    public boolean isRealTime() {
        return isRealTime;
    }


    public void setRealTime(boolean isRealTime) {
        this.isRealTime = isRealTime;
    }


    public int getDeadline() {
        return deadline;
    }


    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public void setNumberOfRequests(int n) {
        this.numberOfRequests+=n;
    }
    public void cutDeadline(){
        this.deadline -= 1;
    }
     
    
}
