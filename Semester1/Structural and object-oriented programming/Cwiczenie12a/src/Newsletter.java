import java.util.ArrayList;
import java.util.Iterator;
public class Newsletter implements Subject{

    int latestArticleId;
    ArrayList<Observer> observerList;

    public Newsletter(){
            observerList = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer observer){
        observerList.add(observer);

    }
    @Override
    public void unregisterObserver(Observer observer){
        observerList.remove(observerList.indexOf(observer));
    }

    @Override
    public void notifyObservers(){
        for(Iterator<Observer> it = observerList.iterator(); it.hasNext();){
            Observer observer = it.next();
            observer.update(latestArticleId);
        }
    }

    public void newArticle(){
        latestArticleId++;
    }


}
