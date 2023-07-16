public class Main {
    public static void main(String[] args) {

        //tworzenie obserwatorow
        ObserverClass obserwator1 = new ObserverClass("O1");
        ObserverClass obserwator2 = new ObserverClass("O2");
        ObserverClass obserwator3 = new ObserverClass("O3");
        ObserverPremium obserwator4 = new ObserverPremium("P01");

        Newsletter newsletter = new Newsletter();

        newsletter.registerObserver(obserwator1);
        newsletter.registerObserver(obserwator2);
        newsletter.registerObserver(obserwator3);
        newsletter.registerObserver(obserwator4);

        System.out.println("Został wrzucony pierwszy artykuł: ");
        newsletter.newArticle();
        newsletter.notifyObservers();
        System.out.println();
        System.out.println("Został wrzucony drugi artykuł: ");
        newsletter.newArticle();
        newsletter.notifyObservers();



        newsletter.unregisterObserver(obserwator2);

        System.out.println();
        System.out.println("Został wrzucony trzeci artykuł, lecz obserwator o numerze 2 odsubskrybował newsletter: ");
        newsletter.newArticle();
        newsletter.notifyObservers();


    }
}