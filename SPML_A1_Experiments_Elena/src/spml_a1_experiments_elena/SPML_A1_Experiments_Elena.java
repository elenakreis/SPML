/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_a1_experiments_elena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author Eier
 */
public class SPML_A1_Experiments_Elena {

    static int EDGE_COUNTER = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //printArray(randomWeights(5, 8, 1, 10));
        
        int nrVertices = 9;
        Graph graph = new Graph(nrVertices);
        graph.fillWeights(randomWeights(nrVertices, 8, 1, 10));
        MST_PRIM(graph);
        System.out.println(graph);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("Number of times an edge was considered: " + EDGE_COUNTER);
        System.out.println("Runtime in milliseconds: " + totalTime);
    }

    private static void MST_PRIM(Graph g) {
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
        for (int i = 0; i < nrEdges-(nrVertices-1); i++) { //add remaining edges INDEX CORRECT???
            int x,y;
            do {
                x = ran.nextInt(nrVertices);
                y = ran.nextInt(nrVertices);
            } while (x == y || randomWeights[x][y]!=-1); 
                    //generate random spot (not on diagonal and not already filled)
            rWeight = low + ran.nextInt(high - low);
            randomWeights[x][y]= rWeight;
            randomWeights[y][x]= rWeight; 
        }
        System.out.println("done");
        for(int row = 0; row < nrVertices; row++){
            for(int col = 0; col < nrVertices; col++){
                if(randomWeights[row][col]==0){
                    randomWeights[row][col]=-1;
                    randomWeights[col][row]=-1;
                }
            }
        }
        return randomWeights;
    }
    
    private static void printArray (int[][]weights){
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights.length; j++) {
                System.out.print(weights[i][j]+" ");    
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
}
