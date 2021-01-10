import edu.uci.ics.jung.graph.Graph;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


public class Astar {

    private double[] f; // f-value
    private int[] prev; // table of previous nodes
    private static final double dinf = 2147483647;
    private static final int iinf = 2147483647;
    private Graph g;
    private double cost;
    private ArrayList<MyEdge> path;
    private MyNode start, goal;
    private MyNode[] nodes;


    public Astar(GraphProduction gp, MyNode start, MyNode goal) {
        g = gp.getGraph();
        int nodeCount = g.getVertexCount();
        this.start = start;
        this.goal = goal;
        nodes = gp.getNodes();
        LinkedList<MyNode> fifo = new LinkedList<>();

        f = new double[nodeCount];
        prev = new int[nodeCount];
        for(int i = 0; i < nodeCount; i++) {
            f[i] = dinf;
            prev[i] = iinf;
        }
        f[start.getId()]=0;
        fifo.add(start);
        MyNode tmp;
        Double tmpf;
        Collection<MyEdge> tmpEdges;
        while(!fifo.isEmpty()) {
            tmp = fifo.getFirst();
            fifo.removeFirst();
            tmpEdges = g.getOutEdges(tmp);
            for(MyEdge e : tmpEdges) {
                MyNode neighbor = (MyNode)g.getDest(e);
                tmpf= e.getWeight() + neighbor.getHeuristic();
                if( f[neighbor.getId()] > (f[tmp.getId()] + tmpf) ){
                    f[neighbor.getId()] = f[tmp.getId()] + e.getWeight();
                    prev[neighbor.getId()] = tmp.getId();
                    fifo.add(neighbor);
                }
            }
        }
        cost = f[goal.getId()];
    }

    public double getCost() throws AstarException {
        System.out.println(" cost"+cost);
        if(cost==dinf) {
            throw new AstarException();
        }
        return cost;
    }

    public ArrayList<MyEdge> getPath() {
        ArrayList<MyNode> tmppath= new ArrayList<>();
        ArrayList<MyEdge> path= new ArrayList<>();
        int tmpnode=goal.getId(), next;
        System.out.println(tmpnode);
        do {
            tmppath.add(nodes[tmpnode]);
            next=prev[tmpnode];
            tmpnode=next;
            }
            while (prev[tmpnode] !=iinf);
        tmppath.add(nodes[tmpnode]);

        for(int i=(tmppath.size()-1); i>=1 ;i--) {
            path.add((MyEdge) g.findEdge(tmppath.get(i), tmppath.get(i-1)));
        }

        return path;
    }
}
