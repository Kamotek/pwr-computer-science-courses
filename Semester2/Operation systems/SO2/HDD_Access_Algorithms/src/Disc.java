import java.util.Arrays;

public class Disc {
    private AccessData[] discSpots;

    public Disc(int size){
        discSpots = new AccessData[size];
        Arrays.fill(discSpots, new AccessData());
    }

    public AccessData[] getDiscSpots() {
        return discSpots;
    }

    public void setDiscSpots(AccessData[] discSpots) {
        this.discSpots = discSpots;
    }
    
    
}
