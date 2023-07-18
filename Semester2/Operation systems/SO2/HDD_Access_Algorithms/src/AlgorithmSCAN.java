import java.util.ArrayList;

public class AlgorithmSCAN {

    private int doneRequests;
    private int discPlatterRotations;
    private int numberOfNeedleTransitions;
    private Disc disc;
    private RequestsSpawner rs;
    private boolean isRealTime;
    private int doneRealTimeRequests;
    private int deadRealTimeRequests;
    
    
    public AlgorithmSCAN(Disc disc, RequestsSpawner rs, boolean isRealTime){
        this.disc = disc;
        this.rs = rs;
        this.isRealTime = isRealTime;

    }

    public void executePassiveMode(){
        AccessData[] discArray = disc.getDiscSpots();
        ArrayList<Boolean> requestOccurance = (ArrayList<Boolean>) rs.getNormalRequestOccurancePattern();
        ArrayList<Integer> orderOfRespawning = (ArrayList<Integer>) rs.getOrderOfRespawning();
        
        boolean rotatesRight = true;
        int requestOccuranceIterator = 0;
        int orderOfRespawningIterator = 0;
        int needleIterator = 0;
        int maxActiveRequests = 100;

        while(getDoneRequests() < orderOfRespawning.size()){
            setNumberOfNeedleTransitions(1);
            if(requestOccurance.get(requestOccuranceIterator) && maxActiveRequests > 0){
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
                orderOfRespawningIterator++;
                maxActiveRequests--;
            }

            requestOccuranceIterator++;
            if(requestOccuranceIterator==requestOccurance.size())
              requestOccuranceIterator = 0;

            if(discArray[needleIterator].getNumberOfRequests()!=0){
                discArray[needleIterator].setNumberOfRequests(-1);
                this.setDoneRequests(1);
                maxActiveRequests++;
            }

            if(rotatesRight)
                needleIterator++;
            if(!rotatesRight)
                needleIterator--;
            if(needleIterator == discArray.length-1){
                rotatesRight = false;
                setDiscPlatterRotations(1);
            }
            if(needleIterator == 0){
                rotatesRight = true;
                setDiscPlatterRotations(1);
            }
            
        }

    }
    
    
   public void executeRealTimeFDScanMode(){
    AccessData[] discArray = disc.getDiscSpots();
    ArrayList<Boolean> requestOccurance = (ArrayList<Boolean>) rs.getNormalRequestOccurancePattern();
    ArrayList<Integer> orderOfRespawning = (ArrayList<Integer>) rs.getOrderOfRespawning();
    ArrayList<Boolean> requestOccuranceRealtime = (ArrayList<Boolean>) rs.getRealTimeRequestOccurancePattern();
    
    ArrayList<Integer> realtimeQueue = new ArrayList<>();
    
    boolean rotatesRight = true;
    int requestOccuranceIterator = 0;
    int orderOfRespawningIterator = 0;
    int requestOccuranceRealtimeIterator = 0;
    int needleIterator = 0;

    int maxRealtimeRequests = 100;

    while(getDoneRequests() + getDoneRealTimeRequests() + getDeadRealTimeRequests()< orderOfRespawning.size()){

        setNumberOfNeedleTransitions(1);
        if(requestOccurance.get(requestOccuranceIterator) && orderOfRespawningIterator < orderOfRespawning.size()){
            discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
            orderOfRespawningIterator++;
        }
        if(requestOccuranceRealtime.get(requestOccuranceRealtimeIterator) && orderOfRespawningIterator < orderOfRespawning.size() && maxRealtimeRequests > 0){
            discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
            discArray[orderOfRespawning.get(orderOfRespawningIterator)].setRealTime(true);
            discArray[orderOfRespawning.get(orderOfRespawningIterator)].setDeadline(100);
            realtimeQueue.add(orderOfRespawning.get(orderOfRespawningIterator));
            orderOfRespawningIterator++;
            maxRealtimeRequests--;
        }

        requestOccuranceIterator++;
        if(requestOccuranceIterator==requestOccurance.size())
          requestOccuranceIterator = 0;

        if(discArray[needleIterator].getNumberOfRequests()!=0){
            discArray[needleIterator].setNumberOfRequests(-1);
            setDoneRequests(1);
        }

        if(rotatesRight)
            needleIterator++;
        if(!rotatesRight)
            needleIterator--;
        if(needleIterator == discArray.length-1){
            rotatesRight = false;
            setDiscPlatterRotations(1);
        }
        if(needleIterator == 0){
            rotatesRight = true;
            setDiscPlatterRotations(1);
        }

        if(realtimeQueue.size()!=0){
            if(Math.abs(needleIterator-realtimeQueue.get(0)) >= discArray[realtimeQueue.get(0)].getDeadline()){setDeadRealTimeRequests(1);}
            if(Math.abs(needleIterator-realtimeQueue.get(0)) < discArray[realtimeQueue.get(0)].getDeadline()){
                if(needleIterator>realtimeQueue.get(0)){
                    if(!rotatesRight){rotatesRight = true; setDiscPlatterRotations(1);}
                    while(needleIterator >= realtimeQueue.get(0)){
                        needleIterator--;
                        setNumberOfNeedleTransitions(1);

                        if(discArray[needleIterator].getNumberOfRequests()!=0){
                            discArray[needleIterator].setNumberOfRequests(-1);
                            if(!discArray[needleIterator].isRealTime())
                                this.setDoneRequests(1);
                            if(discArray[needleIterator].isRealTime()){
                                this.setDoneRealTimeRequests(1);
                                Integer x = needleIterator;
                                realtimeQueue.remove(x);
                            }
                        }       

                    }             
                    if(needleIterator<=realtimeQueue.get(0)){
                        if(rotatesRight){rotatesRight = false; setDiscPlatterRotations(1);}
                        while(needleIterator <= realtimeQueue.get(0)){

                            needleIterator++;
                            setNumberOfNeedleTransitions(1);
    
                            if(discArray[needleIterator].getNumberOfRequests()!=0){
                                discArray[needleIterator].setNumberOfRequests(-1);
                                if(!discArray[needleIterator].isRealTime())
                                    this.setDoneRequests(1);
                                if(discArray[needleIterator].isRealTime()){
                                    this.setDoneRealTimeRequests(1);
                                    Integer x = needleIterator;
                                    realtimeQueue.remove(x);
                                }
                            }       
                        } 
                    }
                }
            }
        }
    }
}
    


public void executeRealTimeEDFMode(){
    AccessData[] discArray = disc.getDiscSpots();
    ArrayList<Boolean> requestOccurance = (ArrayList<Boolean>) rs.getNormalRequestOccurancePattern();
    ArrayList<Integer> orderOfRespawning = (ArrayList<Integer>) rs.getOrderOfRespawning();

    ArrayList<Boolean> requestOccuranceRealtime = (ArrayList<Boolean>) rs.getRealTimeRequestOccurancePattern();    
    ArrayList<Integer> realtimeQueue = new ArrayList<>();
    
    
    boolean rotatesRight = true;
    int requestOccuranceIterator = 0;
    int orderOfRespawningIterator = 0;
    int needleIterator = 0;
    int requestOccuranceRealtimeIterator = 0;
    int maxRealtimeRequests = 100;

    while(getDoneRequests() < orderOfRespawning.size()){
        setNumberOfNeedleTransitions(1);
        if(requestOccurance.get(requestOccuranceIterator)){
            discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
            orderOfRespawningIterator++;
        }

        requestOccuranceIterator++;
        if(requestOccuranceIterator==requestOccurance.size())
          requestOccuranceIterator = 0;

        if(requestOccuranceRealtime.get(requestOccuranceRealtimeIterator) && orderOfRespawningIterator < orderOfRespawning.size() && maxRealtimeRequests > 0){
            discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
            discArray[orderOfRespawning.get(orderOfRespawningIterator)].setRealTime(true);
            discArray[orderOfRespawning.get(orderOfRespawningIterator)].setDeadline(100);
            realtimeQueue.add(orderOfRespawning.get(orderOfRespawningIterator));
            orderOfRespawningIterator++;
            maxRealtimeRequests--;
        }

        if(discArray[needleIterator].getNumberOfRequests()!=0){
            discArray[needleIterator].setNumberOfRequests(-1);
            this.setDoneRequests(1);
        }

        if(rotatesRight)
            needleIterator++;
        if(!rotatesRight)
            needleIterator--;
        if(needleIterator == discArray.length-1){
            rotatesRight = false;
            setDiscPlatterRotations(1);
        }
        if(needleIterator == 0){
            rotatesRight = true;
            setDiscPlatterRotations(1);
        }

        if(realtimeQueue.size() != 0){
            Integer earliestDeadlineIndex = realtimeQueue.get(0);
            for (int x: realtimeQueue) {
                if(discArray[x].getDeadline() < discArray[earliestDeadlineIndex].getDeadline()){
                    earliestDeadlineIndex = x;
                }
                if(needleIterator>=earliestDeadlineIndex){
                    if(!rotatesRight){rotatesRight = true; setDiscPlatterRotations(1);}
                    while(needleIterator>=earliestDeadlineIndex){
                        needleIterator--;
                        setNumberOfNeedleTransitions(1);
                        if(needleIterator == earliestDeadlineIndex){
                            discArray[needleIterator].setRealTime(false);
                            discArray[needleIterator].setDeadline(0);
                            setDoneRealTimeRequests(1);
                            realtimeQueue.remove(earliestDeadlineIndex);
                        }
                        for (int y : realtimeQueue) {
                            discArray[y].cutDeadline();
                            if(discArray[y].getDeadline()==0){
                                discArray[y].setRealTime(false);
                                setDeadRealTimeRequests(1);
                            }
                        }
                    }

                }
                if(needleIterator<earliestDeadlineIndex){
                    if(rotatesRight){rotatesRight = false; setDiscPlatterRotations(1);}

                    while(needleIterator<=earliestDeadlineIndex){
                        needleIterator++;
                        setNumberOfNeedleTransitions(1);
                        if(needleIterator == earliestDeadlineIndex){
                            discArray[needleIterator].setRealTime(false);
                            discArray[needleIterator].setDeadline(0);
                            setDoneRealTimeRequests(1);
                            realtimeQueue.remove(earliestDeadlineIndex);
                        }
                        for (int y : realtimeQueue) {
                            discArray[y].cutDeadline();
                            if(discArray[y].getDeadline()==0){
                                discArray[y].setRealTime(false);
                                setDeadRealTimeRequests(1);
                            }
                        }
                    }

                }

            }
        
        }
    }


}
    
    public int getDoneRequests() {
        return doneRequests;
    }
    public void setDoneRequests(int x) {
        this.doneRequests += x;
    }
    public int getDiscPlatterRotations() {
        return discPlatterRotations;
    }
    public void setDiscPlatterRotations(int x) {
        this.discPlatterRotations += x;
    }
    public int getNumberOfNeedleTransitions() {
        return numberOfNeedleTransitions;
    }
    public void setNumberOfNeedleTransitions(int x) {
        this.numberOfNeedleTransitions += x;
    }



    public Disc getDisc() {
        return disc;
    }



    public void setDisc(Disc disc) {
        this.disc = disc;
    }



    public RequestsSpawner getRs() {
        return rs;
    }



    public void setRs(RequestsSpawner rs) {
        this.rs = rs;
    }

    public boolean isRealTime() {
        return isRealTime;
    }

    public void setRealTime(boolean isRealTime) {
        this.isRealTime = isRealTime;
    }

    public int getDoneRealTimeRequests() {
        return doneRealTimeRequests;
    }

    public void setDoneRealTimeRequests(int doneRealTimeRequests) {
        this.doneRealTimeRequests += doneRealTimeRequests;
    }

    public int getDeadRealTimeRequests() {
        return deadRealTimeRequests;
    }

    public void setDeadRealTimeRequests(int deadRealTimeRequests) {
        this.deadRealTimeRequests += deadRealTimeRequests;
    }

    


}


/*
if(realTimeQueue.size()!=0){
    if(Math.abs(needleIterator-realTimeQueue.get(0)) < realTimeQueue.get(0)){
        while(needleIterator != realTimeQueue.get(0)){
            for (Integer x : realTimeQueue) {
                discArray[x].cutDeadline();
                if(discArray[x].getDeadline()<=0 && discArray[x].isRealTime()){
                    discArray[x].setRealTime(false);
                    setDeadRealTimeRequests(1);

                }
            }
            if(needleIterator > realTimeQueue.get(0)){
                needleIterator--;
                setNumberOfNeedleTransitions(1);
                
                if(discArray[needleIterator].getNumberOfRequests() != 0){
                    discArray[needleIterator].setNumberOfRequests(-1);
                    if(discArray[needleIterator].isRealTime()){
                        setDoneRealTimeRequests(1);
                        discArray[needleIterator].setRealTime(false);
                        discArray[needleIterator].setDeadline(0);
                        realTimeQueue.remove(0);
                    }
                    setDoneRequests(1);
                }
            }
            if(needleIterator < realTimeQueue.get(0)){
                needleIterator++;
                setNumberOfNeedleTransitions(1);
                if(discArray[needleIterator].getNumberOfRequests() != 0){
                    discArray[needleIterator].setNumberOfRequests(-1);
                    if(discArray[needleIterator].isRealTime()){
                        setDoneRealTimeRequests(1);
                        discArray[needleIterator].setRealTime(false);
                        discArray[needleIterator].setDeadline(0);
                        realTimeQueue.remove(0);
                        
                    }
                    setDoneRequests(1);
                }
            }
            
        }
    }
    if(Math.abs(needleIterator-realTimeQueue.get(0)) >= realTimeQueue.get(0)){
        realTimeQueue.remove(0);
        setDeadRealTimeRequests(1);
    }

    */