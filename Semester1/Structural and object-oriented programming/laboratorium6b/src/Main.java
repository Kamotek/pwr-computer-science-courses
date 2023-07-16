import java.util.Random;

public class Main {
    public static void wyswietlElementyTablicy(Konto[] bank){
        for(int i=0;i<bank.length;i++)
            bank[i].getStan();
    }

    public static Konto znajdowanieNajmniejszegoElementu(Konto[] bank){
        Konto najmniejszeKonto = bank[0];

        for(int i=0;i<bank.length;i++){
            if(najmniejszeKonto.getSaldo()>bank[i].getSaldo()){
                najmniejszeKonto = bank[i];
            }
        }

        return najmniejszeKonto;
    }

    public static void main(String[] args) {
        Random random = new Random();

        int n = 5;

        Konto[] bank = new Konto[n];

        for(int i=0;i<bank.length;i++){
            bank[i] = new Konto(random.nextInt(1000), (random.nextDouble()*1000+100));
        }


        wyswietlElementyTablicy(bank);

        System.out.println("Konto o najmniejszym saldzie ma numer: " + znajdowanieNajmniejszegoElementu(bank).getNrKonta());
    }

}