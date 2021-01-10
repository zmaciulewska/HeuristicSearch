public class MyEdge {

    //private static int edgeCount = 0;
    private double weight;
    private int id;

    public MyEdge(int id, double weight) {
        this.id = id;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int getId() {
        return id;
    }
    public String toString() {
        return "E"+id+"="+weight;
    }

}
