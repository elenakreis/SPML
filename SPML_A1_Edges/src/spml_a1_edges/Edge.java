/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_a1_edges;

/**
 *
 * @author s4841670
 */
public class Edge {
    
    private final Vertex v1;
    private final Vertex v2;
    private final int weight;
    
    public Edge(char v1, char v2, int weight){
        this.v1 = new Vertex(v1,Graph.INF);
        this.v2 = new Vertex(v2,Graph.INF);
        this.weight = weight;
    }
    
    public int getWeight(){
        return weight;
    }
    
    public boolean connectsVertices (Vertex v1, Vertex v2){
        return v1.equals(this.v1)&&v2.equals(this.v2)||v1.equals(this.v2)&&v2.equals(this.v1);
    }
    
    @Override 
    public String toString(){
        return String.format("[%s, %s, weight: %d]",v1.getName(),v2.getName(),weight);
    } 

    public boolean contains(Vertex u) {
        return u.equals(this.v1)||u.equals(this.v2);
    }

    public Vertex other(Vertex u) {
        if(u.equals(this.v1))
            return v2;
        else return v1;
    }
}
