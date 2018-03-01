/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_a1;

import java.util.Arrays;

/**
 *
 * @author Eier
 */
public class Graph {

    private final static int INF = 1000000;
    //   private int nrVertices;
    private int[][] edgeWeights;
    private Vertex[] vertices;
    private Vertex root;
    private final static int ROOT = 0;

    public Graph(int nrVertices) {
//        this.nrVertices = nrVertices;
        this.edgeWeights = new int[nrVertices][nrVertices];
        this.vertices = new Vertex[nrVertices];

        int counter = 1;

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(counter++, INF, -1);
        }

        vertices[ROOT].setKey(0);
    }

    public void fillWeights(int[][] newWeights) {
        this.edgeWeights = newWeights.clone();
    }

    public int getWeight(int vA, int vB) {
        return edgeWeights[vA - 1][vB - 1];
    }

    public boolean hasEdge(int vA, int vB) {
        return getWeight(vA, vB) >= 0;
    }

    public int nrVertices() {
        return edgeWeights.length;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public Vertex[] getNeighbours(Vertex u) {
        Vertex[] neighbours = new Vertex[nrVertices()];
        int counter = 0;
        for (Vertex v : vertices) {
            if (hasEdge(u.getNumber(), v.getNumber())) {
                neighbours[counter++] = v;
            }
        }
        return neighbours;
    }

    @Override
    public String toString() {
        return Arrays.toString(vertices);
    }
}