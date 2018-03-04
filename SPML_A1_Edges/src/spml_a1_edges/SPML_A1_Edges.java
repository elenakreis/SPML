/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_a1_edges;

import java.util.ArrayList;
import java.util.Arrays;
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

        Graph mst = MST_PRIM(graph);
        System.out.println("");
        System.out.println(mst);
    }

    private static Graph MST_PRIM(Graph g) {
        Graph mst = new Graph(g.nrVertices(), new ArrayList<Edge>());

        PriorityQueue<Vertex> pq = initializeQueue(g);

        while (!pq.isEmpty()) {
            Vertex u = pq.poll();
            Edge bestEdge = new Edge(' ', ' ', 0);
            int bestKey = Graph.INF;
            for (Edge e : g.connectedEdges(u)) {
                Vertex vPrime = e.other(u);
                Vertex v = g.findVertex(vPrime);
                int weight = e.getWeight();
                System.out.println(e);           
                if (pq.contains(v) && weight < v.getKey()) {
                    pq.remove(v);
                    v.setKey(weight);
                    pq.add(v);
                    
                    if(v.getKey()<bestKey){
                        bestKey = v.getKey();
                        bestEdge = e;
                    }
                }
            }
            mst.addEdge(bestEdge);
        }
        return mst;
    }

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
