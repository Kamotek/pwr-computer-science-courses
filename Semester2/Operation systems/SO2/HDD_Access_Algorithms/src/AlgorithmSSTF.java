import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AlgorithmSSTF {

    private int doneRequests;
    private int discPlatterRotations;
    private int numberOfNeedleTransitions;
    private Disc disc;
    private RequestsSpawner rs;
    private boolean isRealTime;
    private int doneRealTimeRequests;
    private int deadRealTimeRequests;
    

    public AlgorithmSSTF(Disc disc, RequestsSpawner rs, boolean isRealTime){
        this.disc = disc;
        this.rs = rs;
        this.isRealTime = isRealTime;

    }

    public void executePassiveMode(){
        AccessData[] discArray = disc.getDiscSpots();
        ArrayList<Boolean> requestOccurance = (ArrayList<Boolean>) rs.getNormalRequestOccurancePattern();
        ArrayList<Integer> orderOfRespawning = (ArrayList<Integer>) rs.getOrderOfRespawning();
        ArrayList<Integer> activeQueue = new ArrayList<>();
        
        boolean rotatesRight = true;
        int requestOccuranceIterator = 0;
        int orderOfRespawningIterator = 0;
        int needleIterator = 0;
        

        while(getDoneRequests() < orderOfRespawning.size()){
            setNumberOfNeedleTransitions(1);
            if(requestOccurance.get(requestOccuranceIterator)  && orderOfRespawningIterator < orderOfRespawning.size()){
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
                activeQueue.add(orderOfRespawning.get(orderOfRespawningIterator));
                orderOfRespawningIterator++;
            }

            requestOccuranceIterator++;
            if(requestOccuranceIterator==requestOccurance.size()){
              requestOccuranceIterator = 0;
            }
            
            final int compareNeedle = needleIterator;
            Comparator<Integer> comparator = new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    int diffA = Math.abs(compareNeedle - a);
                    int diffB = Math.abs(compareNeedle - b);
                    return diffA - diffB;
                }
            };

            if(activeQueue.size()>0){
            Collections.sort(activeQueue, comparator);
            if(needleIterator>activeQueue.get(0)){
                setNumberOfNeedleTransitions(1);
                if(rotatesRight){rotatesRight = false; setDiscPlatterRotations(1);}
            }else{
                setNumberOfNeedleTransitions(1);
                if(!rotatesRight){rotatesRight = true; setDiscPlatterRotations(1);}
            }

            needleIterator = activeQueue.get(0);
            activeQueue.remove(0);

            if(discArray[needleIterator].getNumberOfRequests() !=0 ){
                discArray[needleIterator].setNumberOfRequests(-1);
                setDoneRequests(1);
            }
        }
        }

    }
   
    public void executeRealTimeFDScanMode(){
        AccessData[] discArray = disc.getDiscSpots();
        ArrayList<Boolean> requestOccurance = (ArrayList<Boolean>) rs.getNormalRequestOccurancePattern();
        ArrayList<Integer> orderOfRespawning = (ArrayList<Integer>) rs.getOrderOfRespawning();
        ArrayList<Integer> activeQueue = new ArrayList<>();

        ArrayList<Boolean> requestOccuranceRealtime = (ArrayList<Boolean>) rs.getRealTimeRequestOccurancePattern();    
        ArrayList<Integer> realtimeQueue = new ArrayList<>();
     
        
        boolean rotatesRight = true;
        int requestOccuranceIterator = 0;
        int orderOfRespawningIterator = 0;
        int needleIterator = 0;
        int requestOccuranceRealtimeIterator = 0;
        int maxRealtimeRequests = 100;
        

        while(getDoneRequests() + getDeadRealTimeRequests() + getDoneRealTimeRequests() < orderOfRespawning.size()){
            setNumberOfNeedleTransitions(1);
            if(requestOccurance.get(requestOccuranceIterator)  && orderOfRespawningIterator < orderOfRespawning.size()){
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
                activeQueue.add(orderOfRespawning.get(orderOfRespawningIterator));
                orderOfRespawningIterator++;
            }

            requestOccuranceIterator++;
            if(requestOccuranceIterator==requestOccurance.size()){
              requestOccuranceIterator = 0;
            }

            if(requestOccuranceRealtime.get(requestOccuranceRealtimeIterator) && orderOfRespawningIterator < orderOfRespawning.size() && maxRealtimeRequests > 0){
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setRealTime(true);
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setDeadline(100);
                realtimeQueue.add(orderOfRespawning.get(orderOfRespawningIterator));
                orderOfRespawningIterator++;
                maxRealtimeRequests--;
            }
            
            final int compareNeedle = needleIterator;
            Comparator<Integer> comparator = new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    int diffA = Math.abs(compareNeedle - a);
                    int diffB = Math.abs(compareNeedle - b);
                    return diffA - diffB;
                }
            };

            if(activeQueue.size()>0){
            Collections.sort(activeQueue, comparator);
            if(needleIterator>activeQueue.get(0)){
                setNumberOfNeedleTransitions(needleIterator-activeQueue.get(0));
                if(rotatesRight){rotatesRight = false; setDiscPlatterRotations(1);}
            }else{
                setNumberOfNeedleTransitions(activeQueue.get(0)-needleIterator);
                if(!rotatesRight){rotatesRight = true; setDiscPlatterRotations(compareNeedle);}
            }

            needleIterator = activeQueue.get(0);
            activeQueue.remove(0);

            if(discArray[needleIterator].getNumberOfRequests() !=0 ){
                discArray[needleIterator].setNumberOfRequests(-1);
                setDoneRequests(1);
            }
        }
        if(realtimeQueue.size()!=0){
            if(Math.abs(needleIterator-realtimeQueue.get(0)) >= discArray[realtimeQueue.get(0)].getDeadline()){setDeadRealTimeRequests(1);}
            if(Math.abs(needleIterator-realtimeQueue.get(0)) < discArray[realtimeQueue.get(0)].getDeadline()){
                if(needleIterator>realtimeQueue.get(0)){
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
        ArrayList<Integer> activeQueue = new ArrayList<>();

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
            if(requestOccurance.get(requestOccuranceIterator)  && orderOfRespawningIterator < orderOfRespawning.size()){
                discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
                activeQueue.add(orderOfRespawning.get(orderOfRespawningIterator));
                orderOfRespawningIterator++;
            }

            requestOccuranceIterator++;
            if(requestOccuranceIterator==requestOccurance.size()){
              requestOccuranceIterator = 0;
            }
            
            final int compareNeedle = needleIterator;
            Comparator<Integer> comparator = new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    int diffA = Math.abs(compareNeedle - a);
                    int diffB = Math.abs(compareNeedle - b);
                    return diffA - diffB;
                }
            };

            if(activeQueue.size()>0){
                Collections.sort(activeQueue, comparator);
                if(needleIterator>activeQueue.get(0)){
                    setNumberOfNeedleTransitions(needleIterator-activeQueue.get(0));
                    if(rotatesRight){rotatesRight = false; setDiscPlatterRotations(1);}
                }else{
                    setNumberOfNeedleTransitions(activeQueue.get(0)-needleIterator);
                    if(!rotatesRight){rotatesRight = true; setDiscPlatterRotations(compareNeedle);}
                }

                needleIterator = activeQueue.get(0);
                activeQueue.remove(0);

                if(discArray[needleIterator].getNumberOfRequests() !=0 ){
                    discArray[needleIterator].setNumberOfRequests(-1);
                    setDoneRequests(1);
                }
            }
            while(getDoneRequests() < orderOfRespawning.size()-1){
                setNumberOfNeedleTransitions(1);
                if(requestOccurance.get(requestOccuranceIterator) && orderOfRespawningIterator < orderOfRespawning.size()){
                    discArray[orderOfRespawning.get(orderOfRespawningIterator)].setNumberOfRequests(1);
                    activeQueue.add(orderOfRespawning.get(orderOfRespawningIterator));
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
    
                if(activeQueue.get(0) > needleIterator){
                    needleIterator++;
                }
                if(activeQueue.get(0) < needleIterator){
                    needleIterator--;
                }
                if(activeQueue.get(0) == needleIterator){
                    discArray[needleIterator].setNumberOfRequests(-1);
                    activeQueue.remove(0);
                    setDoneRequests(1);
                    if(rotatesRight && (activeQueue.get(0) < needleIterator)){
                        rotatesRight = false;
                        setDiscPlatterRotations(1);
                    }
                    if(!rotatesRight && (activeQueue.get(0) > needleIterator)){
                        rotatesRight = true;
                        setDiscPlatterRotations(1);
                    }
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
