public class App {
    public static void main(String[] args) throws Exception {
        
        //pagingManager parameters
        int RAM_CAPACITY = 50;
        int VIRTUAL_MEMORY_CAPACITY = 500;
      //  int ALGORITHM = 1;

        //procesQueue parameters
        int QUEUE_SIZE = 2000;

        //proces parameters
        int PROCES_SIZE = 5;
        int PROCES_OFFSET = 5;

        ProcessesQueue queue1 = new ProcessesQueue(QUEUE_SIZE, VIRTUAL_MEMORY_CAPACITY, PROCES_SIZE, PROCES_OFFSET);
        ProcessesQueue queue2 = new ProcessesQueue(queue1);
        ProcessesQueue queue3 = new ProcessesQueue(queue1);
        ProcessesQueue queue4 = new ProcessesQueue(queue1);
        ProcessesQueue queue5 = new ProcessesQueue(queue1);


        PagingManager randomManager = new PagingManager(RAM_CAPACITY, VIRTUAL_MEMORY_CAPACITY, 1, queue1.processArray);
        PagingManager fifoManager = new PagingManager(RAM_CAPACITY, VIRTUAL_MEMORY_CAPACITY, 2, queue2.processArray);
        PagingManager optManager = new PagingManager(RAM_CAPACITY, VIRTUAL_MEMORY_CAPACITY, 3, queue3.processArray);
        PagingManager lruManager = new PagingManager(RAM_CAPACITY, VIRTUAL_MEMORY_CAPACITY, 4, queue4.processArray);
        PagingManager alruManager = new PagingManager(RAM_CAPACITY, VIRTUAL_MEMORY_CAPACITY, 5, queue5.processArray);
        
      
    
    }
}

// Main - klasa służąca do wywołania całej symulacji i określenia jej parametrów
// Proces - obiekt zawierający w sobie tablicę liczb, będących referencjami do konkretnych obszarów w pamięci
// * Lokalność odwolan - będziemy prawdopodobnie brali po kilka sąsiadujących ramek czy coś takiego
// RAM - obiekt symulujący pamięć ram, będzie miał w sobie tablicę o sztywno określonej pojemności i indeksach, którym będziemy przypisywać, bądź odpisywać numery indeksów bloków w pamięci
// VirtualMemory - również obiekt mający w sobie tablicę, większą niż ta w RAM,  w której od początku będziemy mieli sztywno wygenerowane dane indeksowane po kolei
// ReferenceManager, czyli obiekt posiadający tablicę par liczb, gdzie po otrzymaniu żądań będzie parował miejsca w Ramie z danymi w VirtualMemory, bądź też rozkazywał RAMowi zwolnić sloty zgodnie z algorytmami
// Klasy algorytmów, których parametrami będzie oczywiście nasz menadżer

// RAM - potrzebujemy znac kolejnosc zaladowania stron, znac ilosc wywolan indywidualnej strony