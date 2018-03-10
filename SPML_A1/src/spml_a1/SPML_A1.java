/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_a1;

/**
 *
 * @author Eier
 */
public class SPML_A1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //printArray(randomWeights(5, 8, 1, 10));
        
        MST_Prim mst = new MST_Prim();
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Number of times an edge was considered: " + MST_Prim.EDGE_COUNTER);
        System.out.println("Runtime in milliseconds: " + totalTime);
    }
}
