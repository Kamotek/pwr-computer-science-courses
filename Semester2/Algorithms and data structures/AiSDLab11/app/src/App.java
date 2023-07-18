public class App {
    public static void main(String[] args) throws Exception {

        System.out.println();
        Graph graph = new Graph();

        graph.addNode(0, 0, 0);

        graph.addNode(0, 1, 10);
        graph.addNode(0, 3, 30);
        graph.addNode(0, 4, 100);
        graph.addNode(1, 2, 50);
        graph.addNode(2, 4, 10);
        graph.addNode(3, 2, 20);
        graph.addNode(3, 4, 60);
        graph.addNode(4);

        graph.showAllAdjacencies();

        System.out.println();

        graph.showNodeAdjacency("Nysa");

        System.out.println();

        graph.findShortestPath(4);

        System.out.println();

        graph.deepFirstSearch();
    }
}
