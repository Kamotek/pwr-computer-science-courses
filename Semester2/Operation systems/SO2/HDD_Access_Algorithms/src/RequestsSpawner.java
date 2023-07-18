import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RequestsSpawner {

    private int realTimeRequestsSeed;
    private int normalRequestsSeed;
    private List<Boolean> normalRequestOccurancePattern;
    private List<Boolean> realTimeRequestOccurancePattern;
    private List<Integer> orderOfRespawning;

    public RequestsSpawner(int realTimeRequestsSeed, int normalRequestsSeed, int discSize) {
        this.realTimeRequestsSeed = realTimeRequestsSeed;
        this.normalRequestsSeed = normalRequestsSeed;
        // normalRequestsSeed fast manual - 2 - 50% odds, 3 - 33%, 5 - 20%, 10 - 10%, 25 - 4% etc
        // realtimeRequestsSeed fast manual - same as normal but more precise

        this.normalRequestOccurancePattern = new ArrayList<>();
        this.realTimeRequestOccurancePattern = new ArrayList<>();
        this.orderOfRespawning = new ArrayList<>();

        normalRequestOccurancePattern.add(true);
        realTimeRequestOccurancePattern.add(true);

        Random random = new Random();
        for(int i = 0; i<100;i++){
            if(random.nextInt(100)%normalRequestsSeed==0)
                normalRequestOccurancePattern.add(true);
            if(random.nextInt(100)%normalRequestsSeed!=0)
                normalRequestOccurancePattern.add(false);
        }
        for(int i = 0; i<1000;i++){
            if(random.nextInt(1000)%realTimeRequestsSeed==0)
                realTimeRequestOccurancePattern.add(true);
            if(random.nextInt(1000)%realTimeRequestsSeed!=0)
                realTimeRequestOccurancePattern.add(false);
        }

        for(int i=0;i<discSize;i++){
            orderOfRespawning.add(i);
        }

        Collections.shuffle(orderOfRespawning);

         for(int i=(orderOfRespawning.size()/10);i<orderOfRespawning.size();i++){
             orderOfRespawning.remove(i);
         }


    }

    public int getRealTimeRequestsSeed() {
        return realTimeRequestsSeed;
    }

    public int getNormalRequestsSeed() {
        return normalRequestsSeed;
    }

    public List<Boolean> getNormalRequestOccurancePattern() {
        return normalRequestOccurancePattern;
    }

    public List<Boolean> getRealTimeRequestOccurancePattern() {
        return realTimeRequestOccurancePattern;
    }

    public void setRealTimeRequestsSeed(int realTimeRequestsSeed) {
        this.realTimeRequestsSeed = realTimeRequestsSeed;
    }

    public void setNormalRequestsSeed(int normalRequestsSeed) {
        this.normalRequestsSeed = normalRequestsSeed;
    }

    public void setNormalRequestOccurancePattern(List<Boolean> normalRequestOccurancePattern) {
        this.normalRequestOccurancePattern = normalRequestOccurancePattern;
    }

    public void setRealTimeRequestOccurancePattern(List<Boolean> realTimeRequestOccurancePattern) {
        this.realTimeRequestOccurancePattern = realTimeRequestOccurancePattern;
    }

    public List<Integer> getOrderOfRespawning() {
        return orderOfRespawning;
    }

    

    


    

    


}
