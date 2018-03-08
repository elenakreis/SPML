/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_a1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Eier
 */
public class SPML_A1 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Graph graph = new Graph(9);
        int [][] weights = {
            {-1,4,-1,-1,-1,-1,-1,8,-1},
            {4,-1,8,-1,-1,-1,-1,11,-1},
            {-1,8,-1,7,-1,4,-1,-1,2},
            {-1,-1,7,-1,9,14,-1,-1,-1},
            {-1,-1,-1,9,-1,10,-1,-1,-1},
            {-1,-1,4,14,10,-1,2,-1,-1},
            {-1,-1,-1,-1,-1,2,-1,1,6},
            {8,11,-1,-1,-1,-1,1,-1,7},
            {-1,-1,2,-1,-1,-1,6,7,-1}
        };
        graph.fillWeights(weights);	
        MST_PRIM(graph);
        System.out.println(graph);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        
        System.out.println("Runtime in nanoseconds: " + totalTime);
    }
    
    private static void MST_PRIM(Graph g) {
        PriorityQueue <Vertex> pq = initializeQueue(g);
        while(!pq.isEmpty()) {
            Vertex u = pq.poll();
            for(Vertex v:g.getNeighbours(u)){
                if(v != null){
                    int weight = g.getWeight(u.getNumber(), v.getNumber());
                    if(pq.contains(v) && weight < v.getKey()) { 
                        pq.remove(v);
                        v.setParent(u.getNumber());                    
                        v.setKey(weight);
                        pq.add(v);
                    }
                }
            }
        }
    }
    
    private static PriorityQueue <Vertex> initializeQueue(Graph g) {
        Comparator <Vertex> comparator = new VertexComparator();
        PriorityQueue <Vertex> pq = new PriorityQueue<>(g.nrVertices(), comparator);
        
        pq.addAll(Arrays.asList(g.getVertices()));
        return pq;
    }
}
