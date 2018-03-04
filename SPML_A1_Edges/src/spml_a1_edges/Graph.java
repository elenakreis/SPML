/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_a1_edges;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Eier
 */
public class Graph {

    public final static int INF = 1000000;
    //   private int nrVertices;
    private final ArrayList<Edge> edges;
    private final Vertex[] vertices;
    private final static int ROOT = 0;

    public Graph(int nrVertices, ArrayList<Edge> edges) {
//        this.nrVertices = nrVertices;
        this.vertices = new Vertex[nrVertices];
        this.edges = edges;

        int characterCount = 65;

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex((char) characterCount++, INF);
        }

        vertices[ROOT].setKey(0);
    }

    public void addEdge(Edge newEdge) {
        this.edges.add(newEdge);
    }

    public Vertex[] getVertices() {
        return vertices;
    }
    
    public int nrVertices() {
        return vertices.length;
    }

    @Override
    public String toString() {
        return "Vertices: " + Arrays.toString(vertices) +"\n"
                + "Edges: " + edges;
    }

    public ArrayList<Edge> connectedEdges(Vertex u) {
        ArrayList<Edge> connectedEdges = new ArrayList<Edge>();
        
        for (Edge edge : edges) {
            if(edge.contains(u))
                connectedEdges.add(edge);
        }
        return connectedEdges;  
    }
    
    //remove??
    private boolean hasEdge(Vertex v, Vertex u) {
        for (Edge edge : edges) {
            if(edge.connectsVertices(v,u))
                return true;
        }
        return false;
    }
}
