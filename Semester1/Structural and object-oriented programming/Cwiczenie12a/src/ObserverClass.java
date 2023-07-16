public class ObserverClass implements Observer{

    String observatorName;

    public ObserverClass(String name){
        this.observatorName = name;
    }
    @Override
    public void update(int latestArticleId){
        System.out.println(observatorName + " otrzymał informację o artykule numer: " + latestArticleId);
    }
}
