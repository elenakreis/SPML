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
    private int parent;
    private final int NR;
    
    public Vertex(int nr, int key, int parent) {
        this.NR = nr;
        this.key = key;
        this.parent = parent;
    }
    
    public void setKey(int newKey) {
        key = newKey;
    }
    
    public void setParent(int newParent) {
        parent = newParent;
    }
    
    public int getKey() {
        return key;
    }
    
    public int getParent() {
        return parent;
    }
    
    public int getNumber() {
        return NR;
    }
    
    @Override
    public String toString() {
        return String.format("[%d,%d]",NR,parent);
    }
}
