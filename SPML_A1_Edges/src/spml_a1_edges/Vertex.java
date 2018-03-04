/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_a1_edges;

/**
 *
 * @author Eier
 */
public class Vertex {
    private int key;
    private final char name;
    
    public Vertex(char name, int key) {
        this.name = name;
        this.key = key;
    }
    
    public void setKey(int newKey) {
        key = newKey;
    }
    
    public int getKey() {
        return key;
    }

    public char getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return String.format("[%s, key: %d]",name,key);
    }
    
    public boolean equals(Vertex other){
        return name == other.name;
    }
}
