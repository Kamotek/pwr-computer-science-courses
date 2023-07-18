import java.util.ArrayList;

public class AlgorithmCSCAN {

    private int doneRequests;
    private int discPlatterRotations;
    private int numberOfNeedleTransitions;
    private int numberOfMagicDiscTeleportations;
    private Disc disc;
    private RequestsSpawner rs;
    private boolean isRealTime;
    private int doneRealTimeRequests;
    private int deadRealTimeRequests;
    
    
    public AlgorithmCSCAN(Disc disc, RequestsSpawner rs, boolean isRealTime){
        this.disc = disc;
        this.rs = rs;
        this.isRealTime = isRealTime;

    }

    public void executePassiveMode(){
        AccessData[] discArray = disc.getDiscSpots();
        ArrayList<Boolean> requestOccurance = (ArrayList<Boolean>) rs.getNormalRequestOccurancePattern();
        ArrayList<Integer> orderOfRespawning = (ArrayList<Integer>) rs.getOrderOfRespawning();
        
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

            needleIterator++;
            if(needleIterator == discArray.length-1){
                needleIterator = 0;
                setNumberOfMagicDiscTeleportations(1);

            }
            
        }

    }

    public void executeRealTimeFDScanMode(){
        AccessData[] discArray = disc.getDiscSpots();
        ArrayList<Boolean> requestOccurance = (ArrayList<Boolean>) rs.getNormalRequestOccurancePattern();
        ArrayList<Integer> orderOfRespawning = (ArrayList<Integer>) rs.getOrderOfRespawning();

        ArrayList<Boolean> requestOccuranceRealtime = (ArrayList<Boolean>) rs.getRealTimeRequestOccurancePattern();    
        ArrayList<Integer> realtimeQueue = new ArrayList<>();
        
        int requestOccuranceIterator = 0;
        int orderOfRespawningIterator = 0;
        int needleIterator = 0;
        int requestOccuranceRealtimeIterator = 0;
        int maxActiveRequests = 100;
        int maxRealtimeRequests = 100;

        boolean rotatesRight = true;



        while(getDoneRequests() +getDeadRealTimeRequests() + getDoneRealTimeRequests()< orderOfRespawning.size()){
            setNumberOfNeedleTransitions(1);
            if(requestOccurance.get(requestOccuranceIterator) && maxActiveRequests >0){
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
                orderOfRespawningIterator++;
                maxActiveRequests--;
            }

            requestOccuranceIterator++;
            if(requestOccuranceIterator==requestOccurance.size())
              requestOccuranceIterator = 0;

            if(requestOccuranceRealtime.get(requestOccuranceRealtimeIterator) && orderOfRespawningIterator < orderOfRespawning.size() && maxActiveRequests > 0 && maxRealtimeRequests > 0){
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setRealTime(true);
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setDeadline(100);
                realtimeQueue.add(orderOfRespawning.get(orderOfRespawningIterator));
                orderOfRespawningIterator++;
                maxActiveRequests--;
                maxRealtimeRequests--;
            }

            if(discArray[needleIterator].getNumberOfRequests()!=0){
                discArray[needleIterator].setNumberOfRequests(-1);
                this.setDoneRequests(1);
                maxActiveRequests++;
            }

            needleIterator++;
            if(needleIterator == discArray.length-1){
                needleIterator = 0;
                setNumberOfMagicDiscTeleportations(1);
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
                                if(!discArray[needleIterator].isRealTime()){
                                    this.setDoneRequests(1);
                                    maxActiveRequests++;
                                }
                                if(discArray[needleIterator].isRealTime()){
                                    this.setDoneRealTimeRequests(1);
                                    maxActiveRequests++;
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
                                    if(!discArray[needleIterator].isRealTime()){
                                        this.setDoneRequests(1);
                                        maxActiveRequests++;
                                    }
                                    if(discArray[needleIterator].isRealTime()){
                                        this.setDoneRealTimeRequests(1);
                                        Integer x = needleIterator;
                                        realtimeQueue.remove(x);
                                        maxActiveRequests++;
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
        
        int requestOccuranceIterator = 0;
        int orderOfRespawningIterator = 0;
        int needleIterator = 0;
        int requestOccuranceRealtimeIterator = 0;
        int maxActiveRequests = 100;
        int maxRealtimeRequests = 100;

        while(getDoneRequests() < orderOfRespawning.size()){
            setNumberOfNeedleTransitions(1);
            if(requestOccurance.get(requestOccuranceIterator)&& maxActiveRequests>0){
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
                orderOfRespawningIterator++;
                maxActiveRequests--;
            }

            requestOccuranceIterator++;
            if(requestOccuranceIterator==requestOccurance.size())
              requestOccuranceIterator = 0;

              if(requestOccuranceRealtime.get(requestOccuranceRealtimeIterator) && orderOfRespawningIterator < orderOfRespawning.size() && maxActiveRequests > 0 && maxRealtimeRequests > 0){
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setRealTime(true);
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setDeadline(100);
                realtimeQueue.add(orderOfRespawning.get(orderOfRespawningIterator));
                orderOfRespawningIterator++;
                maxActiveRequests--;
                maxRealtimeRequests--;
            }

            if(discArray[needleIterator].getNumberOfRequests()!=0){
                discArray[needleIterator].setNumberOfRequests(-1);
                this.setDoneRequests(1);
                maxActiveRequests++;
            }

            needleIterator++;
            if(needleIterator == discArray.length-1){
                needleIterator = 0;
                setNumberOfMagicDiscTeleportations(1);

            }

            if(realtimeQueue.size() != 0){
                Integer earliestDeadlineIndex = realtimeQueue.get(0);
                for (int x: realtimeQueue) {
                    if(discArray[x].getDeadline() < discArray[earliestDeadlineIndex].getDeadline()){
                        earliestDeadlineIndex = x;
                    }
                    if(needleIterator>=earliestDeadlineIndex){
                        while(needleIterator>=earliestDeadlineIndex){
                            needleIterator--;
                            setNumberOfNeedleTransitions(1);
                            if(needleIterator == earliestDeadlineIndex){
                                discArray[needleIterator].setRealTime(false);
                                discArray[needleIterator].setDeadline(0);
                                setDoneRealTimeRequests(1);
                                realtimeQueue.remove(earliestDeadlineIndex);
                                maxActiveRequests++;
                            }
                            for (int y : realtimeQueue) {
                                discArray[y].cutDeadline();
                                if(discArray[y].getDeadline()==0){
                                    discArray[y].setRealTime(false);
                                    setDeadRealTimeRequests(1);
                                    maxActiveRequests++;
                                }
                            }
                        }

                    }
                    if(needleIterator<earliestDeadlineIndex){
                        while(needleIterator<=earliestDeadlineIndex){
                            needleIterator++;
                            setNumberOfNeedleTransitions(1);
                            if(needleIterator == earliestDeadlineIndex){
                                discArray[needleIterator].setRealTime(false);
                                discArray[needleIterator].setDeadline(0);
                                setDoneRealTimeRequests(1);
                                realtimeQueue.remove(earliestDeadlineIndex);
                                maxActiveRequests++;
                            }
                            for (int y : realtimeQueue) {
                                discArray[y].cutDeadline();
                                if(discArray[y].getDeadline()==0){
                                    discArray[y].setRealTime(false);
                                    setDeadRealTimeRequests(1);
                                    maxActiveRequests++;
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

    public int getNumberOfMagicDiscTeleportations() {
        return numberOfMagicDiscTeleportations;
    }

    public void setNumberOfMagicDiscTeleportations(int x) {
        this.numberOfMagicDiscTeleportations += x;
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
