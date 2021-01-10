import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.io.*;
import java.util.Scanner;

public class GraphProduction {

    private DirectedSparseMultigraph g;
    private MyNode[] nodes;
    private MyNode start, goal;
    private int nodesAmount, edgesAmount;
    public GraphProduction(String filename) throws FileNotFoundException {

        File f = new File(filename);
        Scanner sc = new Scanner(f);
        g = new DirectedSparseMultigraph<MyNode, MyEdge>();
        nodesAmount = sc.nextInt();
        nodes = new MyNode[nodesAmount];
        edgesAmount = sc.nextInt();

        for( int i = 0; i < nodesAmount; i++) {

            double heuristic = sc.nextDouble();
            nodes[i] = new MyNode(i, heuristic);
            g.addVertex(nodes[i]);
        }

        for( int i = 0; i < edgesAmount; i++) {
            double weight = sc.nextDouble();
            int start = sc.nextInt();
            int end = sc.nextInt();
            g.addEdge(new MyEdge(i, weight), nodes[(start)], nodes[(end)], EdgeType.DIRECTED);
        }
        start = nodes[sc.nextInt()];

        goal = nodes[sc.nextInt()];

        sc.close();
    }

    public DirectedSparseMultigraph getGraph() {
        return g;
    }

    public MyNode[] getNodes() {
        return nodes;
    }

    public MyNode getGoal() {
        return goal;
    }

    public MyNode getStart() {
        return start;
    }

    public int getNodesAmount() {
        return nodesAmount;
    }

    public int getEdgesAmount() {
        return edgesAmount;
    }
}
