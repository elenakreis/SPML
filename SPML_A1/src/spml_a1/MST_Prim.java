/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_a1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author Elena
 */
public class MST_Prim {

    public static int EDGE_COUNTER = 0;

    public MST_Prim() {
        int nrVertices = 9;
        int nrEdges = 10; // needs to range from nrVertices-1 to 1/2*nrVertices*(nrVertices-1)
        Graph graph = new Graph(nrVertices);
        int[][] weights = randomWeights(nrVertices, nrEdges, 1, 10);
        //int[][] weights = exampleGraph();
        printArray(weights);
        graph.fillWeights(weights);
        mst_prim(graph);
        System.out.println(graph);
    }

    private static void mst_prim(Graph g) {
        PriorityQueue<Vertex> pq = initializeQueue(g);
        while (!pq.isEmpty()) {
            Vertex u = pq.poll();
            for (Vertex v : g.getNeighbours(u)) {
                int weight = g.getWeight(u.getNumber(), v.getNumber());
                if (pq.contains(v) && weight < v.getKey()) {
                    pq.remove(v);
                    v.setParent(u.getNumber());
                    v.setKey(weight);
                    pq.add(v);
                    EDGE_COUNTER++;
                }
            }
        }
    }

    private static PriorityQueue<Vertex> initializeQueue(Graph g) {
        Comparator<Vertex> comparator = new VertexComparator();
        PriorityQueue<Vertex> pq = new PriorityQueue<>(g.nrVertices(), comparator);

        pq.addAll(Arrays.asList(g.getVertices()));
        return pq;
    }

    private static int[][] randomWeights(int nrVertices, int nrEdges, int low, int high) {
        int[][] randomWeights = fillArray(nrVertices);
        Random ran = new Random();

        int rWeight = low + ran.nextInt(high - low);
        randomWeights[0][1] = rWeight;
        randomWeights[1][0] = rWeight;
        //connect v0 to v1 with a random weight

        for (int i = 2; i < nrVertices; i++) {  //create minimum tree
            rWeight = low + ran.nextInt(high - low); //generate random weight
            int randomIndex = ran.nextInt(i);   //choose one of the vertices in the tree randomly
            randomWeights[i][randomIndex] = rWeight;
            randomWeights[randomIndex][i] = rWeight;
        }
        for (int i = 0; i < nrEdges - (nrVertices - 1); i++) { //add remaining edges
            int x, y;
            do {
                x = ran.nextInt(nrVertices);
                y = ran.nextInt(nrVertices);
            } while (x == y || randomWeights[x][y] != -1);
            //generate random spot (not on diagonal and not already filled)
            rWeight = low + ran.nextInt(high - low);
            randomWeights[x][y] = rWeight;
            randomWeights[y][x] = rWeight;
        }
        return randomWeights;
    }

    private static void printArray(int[][] weights) {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights.length; j++) {
                if (weights[i][j] == -1) {
                    System.out.print("/ ");
                } else {
                    System.out.print(weights[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private static int[][] fillArray(int nrVertices) {
        int[][] emptyArray = new int[nrVertices][nrVertices];
        for (int i = 0; i < nrVertices; i++) {
            for (int j = 0; j < nrVertices; j++) {
                emptyArray[i][j] = -1;
            }
        }
        return emptyArray;
    }

    /**
     * Graph weights from the lecture slides. Used for testing.
     *
     * @return example graph weights
     */
    private int[][] exampleGraph() {
        int[][] exampleGraph
                = {
                    {0, 4, 0, 0, 0, 0, 0, 8, 0}, //A to the others
                    {4, 0, 8, 0, 0, 0, 0, 11, 0}, //B to the others
                    {0, 8, 0, 7, 0, 4, 0, 0, 2}, //C
                    {0, 0, 7, 0, 9, 14, 0, 0, 0}, //D
                    {0, 0, 0, 9, 0, 10, 0, 0, 0,}, //E
                    {0, 0, 4, 14, 10, 0, 2, 0, 0,}, //F
                    {0, 0, 0, 0, 0, 2, 0, 1, 6}, //G
                    {8, 11, 0, 0, 0, 0, 1, 0, 7}, //H
                    {0, 0, 2, 0, 0, 0, 6, 7, 0} //I
                };
        return exampleGraph;
    }
}
