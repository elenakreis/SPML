/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_a1_edges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Eier
 */
public class SPML_A1_Edges {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Edge> edges = initializeEdges();
        Graph graph = new Graph(9, edges);

        System.out.println(graph);
        System.out.println("");
        Graph mst = MST_PRIM(graph);
        System.out.println("");
        System.out.println(mst);
    }

    private static Graph MST_PRIM(Graph g) {

        PriorityQueue<Vertex> unReachSet = initializeQueue(g);
        ArrayList<Vertex> reachSet = new ArrayList<Vertex>();
        Graph mst = new Graph(g.nrVertices(), new ArrayList<Edge>());

        Vertex root = unReachSet.poll();
        reachSet.add(root);

        while (!unReachSet.isEmpty()) {
            Vertex x = unReachSet.poll();
            //reachSet.add(x);
            ArrayList<Edge> connectedEdges = g.connectedEdges(x);
            connectedEdges = filterEdges(connectedEdges, reachSet);
            
            int bestKey = Graph.INF;
            Edge bestEdge = new Edge(' ', ' ', 0);
            for (Edge e : connectedEdges) {
                Vertex y = e.other(x);
                unReachSet.remove(y);
                y.setKey(e.getWeight());
                unReachSet.add(y);
                
                if(y.getKey()<bestKey){
                    bestKey = y.getKey();
                    bestEdge = e;
                }
            }
            if(bestEdge.getWeight()>0)
                mst.addEdge(bestEdge);
            reachSet.add(x);

            
            //add e to mst
        }

        return mst;
    }

    private static ArrayList<Edge> filterEdges(ArrayList<Edge> edges, ArrayList<Vertex> reachSet) {
        ArrayList<Edge> remove = new ArrayList<Edge>();
        for (Edge e : edges) {
            boolean connects = false;
            for (Vertex v : reachSet) {
                if (e.contains(v)) {
                    connects = true;
                }
            }
            if (connects) { //changed to prevent cycles
                remove.add(e);
            }
        }
        edges.removeAll(remove);
        return edges;
    }
    /*
     private static PriorityQueue<Edge> initializeQueue(Graph g){
     Comparator<Edge> comparator = new EdgeComparator();
     PriorityQueue<Edge> pq = new PriorityQueue<>(g.nrEdges(), comparator);

     pq.addAll(g.getEdges());
     return pq;
     }
     */

    /*
     private static Graph MST_PRIM(Graph g) {
     Graph mst = new Graph(g.nrVertices(), new ArrayList<Edge>());

     PriorityQueue<Vertex> pq = initializeQueue(g);
     int counter = 0;
     while (!pq.isEmpty()) {
     System.out.println("queue: " + pq);
     Vertex u = pq.poll();
     //System.out.println("("+counter+") head of queue:" + u);
     Edge bestEdge = new Edge(' ', ' ', 0);
     int bestKey = Graph.INF;
     for (Edge e : g.connectedEdges(u)) {
     Vertex vPrime = e.other(u);
     Vertex v = g.findVertex(vPrime);
     int weight = e.getWeight();
     //System.out.println(e);   
     System.out.println(v);
     System.out.println(pq.contains(v));
     if (pq.contains(v) && weight < v.getKey()) {
     pq.remove(v);
     v.setKey(weight);
     pq.add(v);
     // System.out.println("key of "+ v.getName() +  v.getKey());
     if(v.getKey()<bestKey){
     bestKey = v.getKey();
     System.out.println("Best key: " + v.getName() + "\n");
     bestEdge = e;
     }
     }
     }
     //System.out.println(bestKey);
     mst.addEdge(bestEdge);
     counter++;
     }
     return mst;
     }
     */
    private static PriorityQueue<Vertex> initializeQueue(Graph g) {
        Comparator<Vertex> comparator = new VertexComparator();
        PriorityQueue<Vertex> pq = new PriorityQueue<>(g.nrVertices(), comparator);

        pq.addAll(Arrays.asList(g.getVertices()));
        return pq;
    }

    private static ArrayList<Edge> initializeEdges() {
        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge('A', 'B', 4));
        edges.add(new Edge('A', 'H', 8));
        edges.add(new Edge('B', 'H', 11));
        edges.add(new Edge('B', 'C', 8));
        edges.add(new Edge('H', 'I', 7));
        edges.add(new Edge('H', 'G', 1));
        edges.add(new Edge('I', 'C', 2));
        edges.add(new Edge('I', 'G', 6));
        edges.add(new Edge('C', 'F', 4));
        edges.add(new Edge('G', 'F', 2));
        edges.add(new Edge('C', 'D', 7));
        edges.add(new Edge('D', 'F', 14));
        edges.add(new Edge('D', 'E', 9));
        edges.add(new Edge('F', 'E', 10));
        return edges;
    }
}
