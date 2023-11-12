public class KlasaC extends KlasaB{
    private int a = 2;
    public void wypisywanie(){
        System.out.printf("%d %d %d",  a , super.a, ((KlasaA) this).a);
    }

    @Override
    public String toString() {
        return "Process finished with exit code " + a + super.a + ((KlasaA) this).a;
    }
}
