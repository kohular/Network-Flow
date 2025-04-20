package com.example;

public class Main {
    public static void main(String[] args) {

        try {
            String filename;
            if (args.length > 0){
                filename = args[0];
            } else {
                filename = "src/files/test.txt";
            }

            InputParser parser = new InputParser();
            FlowNetwork network = parser.parse(filename);

            int source = 0;
            int sink = network.getNumNodes() - 1;

            EdmondsKarp ek = new EdmondsKarp();
            int maxflow = ek.computeMaxFlow(network,source,sink);

            for (int i = 0; i < network.getNumNodes(); i++){
                for (Edge edge: network.getAdjEdges(i)){

                    if (edge.getCapacity() > 0){
                        System.out.printf("Edge %d -> %d | Flow = %d / %d%n",
                                edge.getFrom(), edge.getTo(), edge.getFlow(), edge.getCapacity());

                    }
                }
            }
            System.out.println("Maximum Flow: " + maxflow);
            network.printNetwork();

        } catch (Exception e){
            System.err.println("Error running the program: " + e.getMessage());
        }
    }
}