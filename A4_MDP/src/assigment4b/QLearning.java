/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment4b;

import assignment4a.*;
import java.util.Random;

/**
 *
 * @author s4841670
 */
public class QLearning {
    
    private final double[] accumulatedReward;
    private final static int STEPS = 200000;
    private final static double LEARNING_RATE = 0.3;
    private final static double GAMMA = 1;
    private double epsilon = 1;
    private final MarkovDecisionProblem mdp;
    private final double[][][] Q;
    private final int width;
    private final int height;

    public QLearning(MarkovDecisionProblem mdp) {
        this.mdp = mdp;
        width = mdp.getWidth();
        height = mdp.getHeight();
        Q = new double[width][height][Action.values().length];
        accumulatedReward = new double[STEPS+1];
    }

    public void doQL() {
        int steps = 0;
        int x = mdp.getStateXPosition();
        int y = mdp.getStateYPosition();
        accumulatedReward[0] = 0;
        do {
            Action a = selectAction(x, y);
            double r = mdp.performAction(a); //this function returns the reward
            
            int xPrime = mdp.getStateXPosition();
            int yPrime = mdp.getStateYPosition();
            double maxPrime = max(Q[xPrime][yPrime]); // max_a' Q[s',a']
            Q[x][y][a.ordinal()] += LEARNING_RATE * (r + GAMMA * maxPrime - Q[x][y][a.ordinal()]);

            if (mdp.isTerminated()) { // restart to reset reward
                mdp.restart();
                xPrime = mdp.getStateXPosition();
                yPrime = mdp.getStateYPosition();
            }
            x = xPrime; // s <- s'
            y = yPrime;

            update(steps);

            epsilon -= 1 / (double)STEPS;
            steps++;
            accumulatedReward[steps] = accumulatedReward[steps-1] + r;      
        } while (steps < STEPS);

    }

    private void update(int steps) {

        if (steps % 500 == 0) {
            //System.out.println(steps + " steps");
        }
        if (steps == STEPS - 1) {
            System.out.println("");
            printPolicy();
        }
    }

    private Action selectAction(int x, int y) {
        Random rand = new Random();
        double prob = rand.nextDouble();
        if (prob < epsilon) {
            return Action.values()[rand.nextInt(Action.values().length)];
        } else {
            return argMax(Q[x][y]);
        }
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

    private Action argMax(double[] array) {
        int index = 0;
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                index = i;
            }
        }
        return Action.values()[index];
    }

    private void printQ() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("State %d %d:\n", j, i);
                for (int k = 0; k < Action.values().length; k++) {
                    System.out.printf("\t %s %f\n", Action.values()[k], Q[j][i][k]);
                }
            }
        }
    }

    private void printPolicy() {
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%s\t", argMax(Q[j][i]));
            }
            System.out.println("");
        }
    }

}
