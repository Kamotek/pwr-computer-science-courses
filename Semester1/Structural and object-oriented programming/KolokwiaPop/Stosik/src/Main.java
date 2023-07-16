public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Stos stosik = new Stos(6);

        stosik.dodawanieElementu(4);
        stosik.dodawanieElementu(7);
        stosik.dodawanieElementu(8);
        stosik.dodawanieElementu(9);
        stosik.dodawanieElementu(3);

        System.out.println(stosik.czyPelny());
        System.out.println("----------------");
        stosik.wyswietlElementyStosu();

        stosik.usuwanieElementu();
        stosik.usuwanieElementu();
        stosik.usuwanieElementu();
        stosik.usuwanieElementu();
        stosik.usuwanieElementu();

        System.out.println(stosik.czyPusty());
        System.out.println("----------------");
        stosik.wyswietlElementyStosu();


    }
}