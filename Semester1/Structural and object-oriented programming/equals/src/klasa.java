public class klasa {
    private int liczba;
    private char znak;

    public klasa(int liczba) {
        this.liczba = liczba;
    }

    public klasa(char znak) {
        this.znak = znak;
    }

    public int getLiczba() {
        return liczba;
    }

    public void setLiczba(int liczba) {
        this.liczba = liczba;
    }

    public char getZnak() {
        return znak;
    }

    public void setZnak(char znak) {
        this.znak = znak;
    }

    @Override
    public boolean equals(Object obiektGlowny) {
        if (this == obiektGlowny) return true;

        klasa klasa = (klasa) obiektGlowny;

        if (liczba != klasa.liczba) return false;
        return znak == klasa.znak;
    }

}
