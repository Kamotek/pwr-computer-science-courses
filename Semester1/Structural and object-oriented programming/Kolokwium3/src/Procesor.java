public class Procesor {
    private String nazwa;
    private Zegar zegar;

    public Procesor(String nazwa, Zegar zegar){
        this.nazwa = nazwa;
        this.zegar = zegar;
    }

    public Zegar getZegar() {
        return zegar;
    }
}
