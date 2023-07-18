public class App {
    public static void main(String[] args) throws Exception {
        int realTimeRequestsSeed = 500;
        int normalRequestsSeed = 5;
        int discSize = 100000;

        RequestsSpawner rs = new RequestsSpawner(realTimeRequestsSeed, normalRequestsSeed, discSize);
        Disc disc = new Disc(discSize);

         System.out.println("=========SCAN Algorithm=========");
         AlgorithmSCAN scan = new AlgorithmSCAN(disc, rs,false);
         scan.executePassiveMode();
         System.out.print("Wykonane procesy: ");
         System.out.println(scan.getDoneRequests());
         System.out.print("Przeskoki głowicy: ");
         System.out.println(scan.getNumberOfNeedleTransitions());
         System.out.print("Zmiany kierunku obrotu talerza: ");
         System.out.println(scan.getDiscPlatterRotations());
        

        System.out.println("=========C-SCAN Algorithm=========");
        AlgorithmCSCAN cscan = new AlgorithmCSCAN(disc, rs, false);
        cscan.executePassiveMode();
        System.out.print("Wykonane procesy: ");
        System.out.println(cscan.getDoneRequests());
        System.out.print("Magiczne przeskoki z końca na początek talerza: ");
        System.out.println(cscan.getNumberOfMagicDiscTeleportations());
        System.out.print("Przeskoki głowicy: ");
        System.out.println(cscan.getNumberOfNeedleTransitions());
        System.out.print("Zmiany kierunku obrotu talerza: ");
        System.out.println(cscan.getDiscPlatterRotations());
        

        System.out.println("=========FCFS Algorithm=========");
        AlgorithmFCFS fcfs = new AlgorithmFCFS(disc, rs, false);
        fcfs.executePassiveMode();
        System.out.print("Wykonane procesy: ");
        System.out.println(fcfs.getDoneRequests());
        // System.out.print("Magiczne przeskoki z końca na początek talerza: ");
        // System.out.println(fcfs.getNumberOfMagicDiscTeleportations());
        System.out.print("Przeskoki głowicy: ");
        System.out.println(fcfs.getNumberOfNeedleTransitions());
        System.out.print("Zmiany kierunku obrotu talerza: ");
        System.out.println(fcfs.getDiscPlatterRotations());

        
        System.out.println("===========================SSTF Algorithm===========================");
        AlgorithmSSTF sstf = new AlgorithmSSTF(disc, rs, false);
        sstf.executePassiveMode();
        System.out.print("Wykonane procesy: ");
        System.out.println(sstf.getDoneRequests());
        // System.out.print("Magiczne przeskoki z końca na początek talerza: ");
        // System.out.println(fcfs.getNumberOfMagicDiscTeleportations());
        System.out.print("Przeskoki głowicy: ");
        System.out.println(sstf.getNumberOfNeedleTransitions());
        System.out.print("Zmiany kierunku obrotu talerza: ");
        System.out.println(sstf.getDiscPlatterRotations());

        System.out.println("===========================SCAN with FD-SCAN= Algorithms===========================");
        AlgorithmSCAN scanfd = new AlgorithmSCAN(disc, rs,false);
        scanfd.executeRealTimeFDScanMode();
        System.out.print("Wykonane procesy: ");
        System.out.println(scanfd.getDoneRequests());
        System.out.print("Przeskoki głowicy: ");
        System.out.println(scanfd.getNumberOfNeedleTransitions());
        System.out.print("Zmiany kierunku obrotu talerza: ");
        System.out.println(scanfd.getDiscPlatterRotations());
        System.out.print("Wykonane procesy realtime: ");
        System.out.println(scanfd.getDoneRealTimeRequests());
        System.out.print("Martwe procesy realtime: ");
        System.out.println(scanfd.getDeadRealTimeRequests());


        System.out.println("===========================CSCAN with FD-SCAN= Algorithms===========================");
        AlgorithmCSCAN cscanfd = new AlgorithmCSCAN(disc, rs,false);
        cscanfd.executeRealTimeFDScanMode();
        System.out.print("Wykonane procesy: ");
        System.out.println(cscanfd.getDoneRequests());
        System.out.print("Przeskoki głowicy: ");
        System.out.println(cscanfd.getNumberOfNeedleTransitions());
        System.out.print("Zmiany kierunku obrotu talerza: ");
        System.out.println(cscanfd.getDiscPlatterRotations());
        System.out.print("Wykonane procesy realtime: ");
        System.out.println(cscanfd.getDoneRealTimeRequests());
        System.out.print("Martwe procesy realtime: ");
        System.out.println(cscanfd.getDeadRealTimeRequests());

        System.out.println("===========================FCFS with FD-SCAN= Algorithms===========================");
        AlgorithmFCFS fcfsfd = new AlgorithmFCFS(disc, rs,false);
        fcfsfd.executeRealTimeFDScanMode();
        System.out.print("Wykonane procesy: ");
        System.out.println(fcfsfd.getDoneRequests());
        System.out.print("Przeskoki głowicy: ");
        System.out.println(fcfsfd.getNumberOfNeedleTransitions());
        System.out.print("Zmiany kierunku obrotu talerza: ");
        System.out.println(fcfsfd.getDiscPlatterRotations());
        System.out.print("Wykonane procesy realtime: ");
        System.out.println(fcfsfd.getDoneRealTimeRequests());
        System.out.print("Martwe procesy realtime: ");
        System.out.println(fcfsfd.getDeadRealTimeRequests());

        System.out.println("===========================SSTF with FD-SCAN= Algorithms=====================");
        AlgorithmSSTF sstffd = new AlgorithmSSTF(disc, rs,false);
        sstffd.executeRealTimeFDScanMode();
        System.out.print("Wykonane procesy: ");
        System.out.println(sstffd.getDoneRequests());
        System.out.print("Przeskoki głowicy: ");
        System.out.println(sstffd.getNumberOfNeedleTransitions());
        System.out.print("Zmiany kierunku obrotu talerza: ");
        System.out.println(sstffd.getDiscPlatterRotations());
        System.out.print("Wykonane procesy realtime: ");
        System.out.println(sstffd.getDoneRealTimeRequests());
        System.out.print("Martwe procesy realtime: ");
        System.out.println(sstffd.getDeadRealTimeRequests());

        System.out.println("===========================CSCAN with EDF Algorithms=====================");
        AlgorithmCSCAN cscanedf = new AlgorithmCSCAN(disc, rs,false);
        cscanedf.executeRealTimeFDScanMode();
        System.out.print("Wykonane procesy: ");
        System.out.println(cscanedf.getDoneRequests());
        System.out.print("Przeskoki głowicy: ");
        System.out.println(cscanedf.getNumberOfNeedleTransitions());
        System.out.print("Zmiany kierunku obrotu talerza: ");
        System.out.println(cscanedf.getDiscPlatterRotations());
        System.out.print("Wykonane procesy realtime: ");
        System.out.println(cscanedf.getDoneRealTimeRequests());
        System.out.print("Martwe procesy realtime: ");
        System.out.println(cscanedf.getDeadRealTimeRequests());

        System.out.println("===========================SCAN with EDF Algorithms=====================");
        AlgorithmSCAN scanedf = new AlgorithmSCAN(disc, rs,false);
        scanedf.executeRealTimeFDScanMode();
        System.out.print("Wykonane procesy: ");
        System.out.println(scanedf.getDoneRequests());
        System.out.print("Przeskoki głowicy: ");
        System.out.println(scanedf.getNumberOfNeedleTransitions());
        System.out.print("Zmiany kierunku obrotu talerza: ");
        System.out.println(scanedf.getDiscPlatterRotations());
        System.out.print("Wykonane procesy realtime: ");
        System.out.println(scanedf.getDoneRealTimeRequests());
        System.out.print("Martwe procesy realtime: ");
        System.out.println(scanedf.getDeadRealTimeRequests());

        System.out.println("===========================FCFS with EDF Algorithms=====================");
        AlgorithmFCFS fcfsedf = new AlgorithmFCFS(disc, rs,false);
        fcfsedf.executeRealTimeFDScanMode();
        System.out.print("Wykonane procesy: ");
        System.out.println(fcfsedf.getDoneRequests());
        System.out.print("Przeskoki głowicy: ");
        System.out.println(fcfsedf.getNumberOfNeedleTransitions());
        System.out.print("Zmiany kierunku obrotu talerza: ");
        System.out.println(fcfsedf.getDiscPlatterRotations());
        System.out.print("Wykonane procesy realtime: ");
        System.out.println(fcfsedf.getDoneRealTimeRequests());
        System.out.print("Martwe procesy realtime: ");
        System.out.println(fcfsedf.getDeadRealTimeRequests());

        System.out.println("===========================SSTF with EDF Algorithms=====================");
        AlgorithmSSTF sstfedf = new AlgorithmSSTF(disc, rs,false);
        sstfedf.executeRealTimeFDScanMode();
        System.out.print("Wykonane procesy: ");
        System.out.println(sstfedf.getDoneRequests());
        System.out.print("Przeskoki głowicy: ");
        System.out.println(sstfedf.getNumberOfNeedleTransitions());
        System.out.print("Zmiany kierunku obrotu talerza: ");
        System.out.println(sstfedf.getDiscPlatterRotations());
        System.out.print("Wykonane procesy realtime: ");
        System.out.println(sstfedf.getDoneRealTimeRequests());
        System.out.print("Martwe procesy realtime: ");
        System.out.println(sstfedf.getDeadRealTimeRequests());

    }
}



/*
 * 1. Requests Spawner tablic boolea dziala , mamy tez kolejnosc indexow w jakich pojawia sie requesty
 * 
 * Gdy sie uwine ze wszystkim trzeba dodac jeszcze max ilosc aktywnych requestow
 */