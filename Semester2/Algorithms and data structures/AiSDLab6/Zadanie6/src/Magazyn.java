public class Magazyn {
    private ListQueue<Klient> kolejkaKlientow;
    private double sumaKwot;
    
    public Magazyn(){
        this.kolejkaKlientow = new ListQueue<>();
        this.sumaKwot = 0;
    }

    public void obsluga() throws EmptyQueueException{
        if(this.kolejkaKlientow.isEmpty()){
            System.out.println("Brak zamowien");
        }else{
            System.out.println("Obsluga magazynu");
            while(!this.kolejkaKlientow.isEmpty()){
                Klient aktualnieObslugiwanyKlient = this.kolejkaKlientow.dequeue();
                double kwota = 0;
                while(!aktualnieObslugiwanyKlient.getKolejkaZamowien().isEmpty()){
                    Zamowienie aktualnieObslugiwaneZamowienie = aktualnieObslugiwanyKlient.getKolejkaZamowien().dequeue();
                    kwota += (aktualnieObslugiwaneZamowienie.getCenaJednostkowa()*aktualnieObslugiwaneZamowienie.getLiczbaSztuk());
                }
                System.out.println("Zlecenie klienta " + aktualnieObslugiwanyKlient.getNazwaKlienta() + " na kwotę " + kwota + " zł, zostało zrealizowane");
                sumaKwot+=kwota;
            }
            System.out.println("Wszystkie zlecenia zrealizowane, laczna suma kwot to: " + sumaKwot + " zł");
        }
    }

    public ListQueue<Klient> getKolejkaKlientow() {
        return kolejkaKlientow;
    }

    public void setKolejkaKlientow(ListQueue<Klient> kolejkaKlientow) {
        this.kolejkaKlientow = kolejkaKlientow;
    }

    public double getSumaKwot() {
        return sumaKwot;
    }

    public void setSumaKwot(double sumaKwot) {
        this.sumaKwot = sumaKwot;
    }
    
}
