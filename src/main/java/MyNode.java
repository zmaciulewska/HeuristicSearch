public class MyNode {

    private int id;
    private double heuristic = 0;


    public MyNode(int id){
        this.id = id;
    }
    public MyNode(int id, double heuristic) {
        this.id = id;
        this.heuristic = heuristic;
    }
    public double getHeuristic() {
        return heuristic;
    }
    public int getId() {
        return id;
    }
    public String toString() {
        return "V"+id+"="+heuristic;
    }
}
