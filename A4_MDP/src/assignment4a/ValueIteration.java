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
    private Action[][] pi;
    private static final double THRESHHOLD = 0.01; // what should threshold be??
    private int width;
    private int height;

    public ValueIteration(MarkovDecisionProblem mdp) {
        this.mdp = mdp;
        width = mdp.getWidth();
        height = mdp.getHeight();
        V = new double[width][height];
        oldV = new double[width][height];
        pi = new Action[width][height];
    }

    public Action[][] doVI() {
        /*
        for (int i = 0; i < height; i++) { // assign V0 arbitrarily
            for (int j = 0; j < width; j++) {
                V[j][i] = 2;
            }
            
        }*/
        do {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) { // loop through states
                    oldV[j][i] = V[j][i];
                    double[] allQ = getQ(j, i, oldV);
                    V[j][i] = max(allQ); // max a of q
                }
            }
        } while (!done());
        printValues();
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) { // loop through states
                double[] allQ = getQ(j, i, V);
                pi[j][i] = argMax(allQ);
            }
        }
        return pi;

    }

    private boolean done() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) { // loop through states
                // if we find s for which difference is bigger than threshold then we are not done
                if (Math.abs(V[j][i] - oldV[j][i]) >= THRESHHOLD) {
                    return false; //
                }
            }
        }
        return true;
    }

    private double[] getQ(int x, int y, double[][] values) {
        double[] qValues = new double[Action.values().length];
        int index = 0;
        for (Action action : Action.values()) {
            double sum = mdp.rewardSum(action, x, y, values);
            //System.out.printf("%d %d, %s, %.2f %n", x, y, action, sum);
            qValues[index] = sum;
            index++;
        }
        return qValues;
    }

    private double max(double[] array) {
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            //System.out.printf("max %.2f %n", array[i]);
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private Action argMax(double[] array) {
        int index = 0;
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            //System.out.printf("argMax %.2f %n", array[i]);
            if (array[i] > max) {
                max = array[i];
                index = i;
            }
        }
        return Action.values()[index];
    }
    
    private void printValues(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%.2f  ",V[j][i]);
            }
            System.out.println("");
        }
    }

}
