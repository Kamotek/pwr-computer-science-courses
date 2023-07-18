public class App {
    public static void main(String[] args) throws Exception {
        //tutaj bedziemy inicjowac wszystko

        //najpierw zrobimy kolejke procesow
        //pozniej zrobimy kopiowanie tych procesow
        //pozniej zrobimy jeszcze jakis array boolean, ze co ile cykli zegara nowy proces bedzie mial wejsc 


        ProcessQueueGenerator pqg = new ProcessQueueGenerator(10, 1);

        pqg.boolArrayGenerator();
    }
}
