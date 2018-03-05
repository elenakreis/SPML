/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_a1_edges;

import java.util.Comparator;

/**
 *
 * @author s4841670
 */
public class EdgeComparator implements Comparator<Edge>{

    @Override
    public int compare(Edge e1, Edge e2) {
        return Integer.compare(e1.getWeight(), e2.getWeight());
    }
    
}
