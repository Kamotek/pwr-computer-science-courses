public class App {
    public static void main(String[] args) throws Exception {
        
        //pagingManager parameters
        int RAM_CAPACITY = 10;
        int VIRTUAL_MEMORY_CAPACITY = 250;


        //procesQueue parameters
        int QUEUE_SIZE = 10;

        //proces parameters
        int PROCES_SIZE = 100;
        int PROCES_OFFSET = 25;

        ProcessesQueue queue1 = new ProcessesQueue(QUEUE_SIZE, VIRTUAL_MEMORY_CAPACITY, PROCES_SIZE, PROCES_OFFSET);
        ProcessesQueue queue2 = new ProcessesQueue(queue1);
        ProcessesQueue queue3 = new ProcessesQueue(queue1);
        ProcessesQueue queue4 = new ProcessesQueue(queue1);

        System.out.println();
        System.out.println("------------------------------");
        System.out.println();
        PagingManager EqualLRUManager = new PagingManager(RAM_CAPACITY, VIRTUAL_MEMORY_CAPACITY, queue1.processArray, 1);
        System.out.println();
        System.out.println("------------------------------");
        System.out.println();
        PagingManager PropLRUManager = new PagingManager(RAM_CAPACITY, VIRTUAL_MEMORY_CAPACITY, queue2.processArray, 2);
        System.out.println();
        System.out.println("------------------------------");
        System.out.println();
        PagingManager FaultControlLRUManager = new PagingManager(RAM_CAPACITY, VIRTUAL_MEMORY_CAPACITY, queue3.processArray, 3);
        System.out.println();
        System.out.println("------------------------------");
        System.out.println();
        PagingManager ZonalLRUManager = new PagingManager(RAM_CAPACITY, VIRTUAL_MEMORY_CAPACITY, queue4.processArray, 4);
        
        

        
      
    
    }
}

