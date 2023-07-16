public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Przecenianie swieta = new PrzecenaSwiateczna();

        Przecenianie wielkanoc = new PrzecenaWielkanocna();

        BigDecimal przecienionaWartosc = wielkanoc;

        System.out.println();
    }
}