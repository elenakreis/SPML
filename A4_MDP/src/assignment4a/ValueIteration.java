/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4a;

/**
 *
 * @author Elena
 */
public class ValueIteration {

    private MarkovDecisionProblem mdp;
    private double[][] V;
    private double[][] oldV;
    private static final double THRESHHOLD = 0.05; // what should threshold be??
    private int width;
    private int height;

    public ValueIteration(MarkovDecisionProblem mdp) {
        this.mdp = mdp;
        width = mdp.getWidth();
        height = mdp.getHeight();
        V = new double[width][height];
        oldV = new double[width][height];
    }

    public void doVI() {
        //int k = 0;
        while (notDone()) {
            //k++;
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) { // loop through states
                    double[] allQ = getQ(i, j);
                    V[i][j] = max(allQ); // max a of q
                }
            }
        }
    }

    private boolean notDone() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) { // loop through states
                // if we find s for which difference is bigger than threshold then we are not done
                if (Math.abs(V[i][j] - oldV[i][j]) >= THRESHHOLD) {
                    return true; // true means not done
                }
            }
        }
        return false;
    }

    private double[] getQ(int x, int y) {
        double[] qValues = new double[Action.values().length];
        int index = 0;
        for (Action action : Action.values()) {
            double sum = 0;
            //mdp.getPPerform()*whatever;
            
            
            qValues[index] = sum;
        }
        return qValues;
    }

    private double max(double[] array) {
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

}
