package com.example;

//Edge Class
public class Edge {
    //Variables to store (attributes)
    private final int from; //denotes the node it is from
    private final int to; //denotes the node it is indicating to
    private final int capacity; //denotes the capacity of data/stuff it can carry
    private int flow; //denotes the amount of data/stuff that is passing through
    private Edge reverse;


    //Constructors
    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    public Edge getReverse() {
        return reverse;
    }

    public void setReverse(Edge reverse) {
        this.reverse = reverse;
    }

    //Getters for all attributes
    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    //Gets the remaining capacity of the edge
    public int residualCapacity(){
        return capacity - flow;
    }

    //Adds the amount to the flow passing the edge
    public void addFlow(int amount){
        flow = flow + amount;
    }

    public void subtractFlow(int reducedFlow) {
        flow -= reducedFlow;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", capacity=" + capacity +
                ", flow=" + flow +
                '}';
    }
}
