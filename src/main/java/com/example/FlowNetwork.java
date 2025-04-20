package com.example;

import java.util.ArrayList;
import java.util.List;

public class FlowNetwork {

    private final int numNodes;
    //Variable to store the edges as a list
    private final List<List<Edge>> adjList;

    //Constructor
    public FlowNetwork(int numNodes){
        this.numNodes = numNodes;
        this.adjList = new ArrayList<>();
        for (int i = 0; i < numNodes; i++){
            adjList.add(new ArrayList<>());
        }
    }

    //adding an edge to the list
    public void addEdge(int from, int to, int capacity){
        if (capacity < 0){
            throw new IllegalArgumentException("Capacity cannot be negative" + from + " -> " + to + " with capacity " + capacity);
        }

        Edge forwardEdge = new Edge(from,to,capacity);
        Edge reverseEdge = new Edge(to,from,0);

        forwardEdge.setReverse(reverseEdge);
        reverseEdge.setReverse(forwardEdge);

        adjList.get(from).add(forwardEdge);
        adjList.get(to).add(reverseEdge);

    }

    public int getNumNodes() {
        return numNodes;
    }

    //Getting all Edges from the list
    public List<Edge> getAdjEdges(int node){
        return adjList.get(node);
    }

    //Printing the network flow for debugging
    public void printNetwork(){
        System.out.println("Flow Network Structure:");

        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + ":");

            if (adjList.get(i).isEmpty()) {
                System.out.println("  No outgoing edges.");
            } else {
                for (Edge edge : adjList.get(i)) {
                    if (edge.getCapacity() > 0) {
                        System.out.printf("  Edge to Node %d | Capacity: %d | Flow: %d / %d\n",
                                edge.getTo(), edge.getCapacity(), edge.getFlow(), edge.getCapacity());
                    }
                }
            }
        }
    }
}
