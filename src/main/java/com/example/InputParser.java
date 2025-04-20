package com.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputParser {

    public FlowNetwork parse(String fileName) throws IOException {
        FileReader file = new FileReader(fileName);
        FlowNetwork flowNetwork;

        try(BufferedReader reader = new BufferedReader(file)) {

            int numNodes = Integer.parseInt(reader.readLine());

            flowNetwork = new FlowNetwork(numNodes);

            String line;
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] lineParts = line.trim().split("\\s+");

                int from = Integer.parseInt(lineParts[0]);
                int to = Integer.parseInt(lineParts[1]);
                int capacity = Integer.parseInt(lineParts[2]);

                if (capacity < 0){
                    throw new IllegalArgumentException("Capacity cannot be negative" + from + " -> " + to + " with capacity " + capacity);
                }

                flowNetwork.addEdge(from, to, capacity);
            }
        }

        return flowNetwork;
    }
}
