public class ObserverPremium implements Observer{
    String observatorName;

    public ObserverPremium(String name){
        this.observatorName = name;
    }
    @Override
    public void update(int latestArticleId){
        System.out.println(observatorName + "  premium otrzymał informację o artykule numer: " + latestArticleId);
    }
}
