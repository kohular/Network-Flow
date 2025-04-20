package com.example;

import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarp {

    public int computeMaxFlow(FlowNetwork network, int source, int sink) {
        int maxflow = 0;
        Edge[] pathTo = new Edge[network.getNumNodes()];
        int pathNum = 1;

        while (bfs(network, source, sink, pathTo)) {
            int bottleneck = Integer.MAX_VALUE;
            int current = sink;

            // Find bottleneck
            while (current != source) {
                Edge edge = pathTo[current];
                bottleneck = Math.min(bottleneck, edge.residualCapacity());
                current = edge.getFrom();
            }

            // Print path and bottleneck (without separate method)
            System.out.print("Path " + pathNum + ": ");

            // Rebuild path in reverse
            StringBuilder path = new StringBuilder();
            current = sink;
            while (current != source) {
                path.insert(0, " -> " + current);
                current = pathTo[current].getFrom();
            }
            path.insert(0, source);
            System.out.println(path);
            System.out.println("Bottleneck: " + bottleneck);
            System.out.println();

            // Apply flow
            current = sink;
            while (current != source) {
                Edge edge = pathTo[current];
                edge.addFlow(bottleneck);
                edge.getReverse().addFlow(-bottleneck);
                current = edge.getFrom();
            }

            maxflow += bottleneck;
            pathNum++;
        }

        System.out.println("Maximum Flow: " + maxflow);
        return maxflow;
    }


    public boolean bfs(FlowNetwork network, int source, int sink, Edge[] pathTo){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[network.getNumNodes()];

        for (int i = 0; i < pathTo.length; i++){
            pathTo[i] = null;
        }

        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()){
            int current = queue.poll();

            for (Edge edge: network.getAdjEdges((current))){
                int to = edge.getTo();

                if (edge.residualCapacity() > 0 && !visited[to]){
                    visited[to] = true;
                    pathTo[to] = edge;
                    queue.add(to);

                    if (to == sink){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
