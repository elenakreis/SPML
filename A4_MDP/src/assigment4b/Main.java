/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment4b;
import assignment4a.*;

/**
 *
 * @author Elena
 */
public class Main {
    public static void main(String[] args) {
        MarkovDecisionProblem mdp = new MarkovDecisionProblem();
        mdp.setInitialState(0, 0);
        QLearning ql = new QLearning(mdp);
        ql.doQL();
    }
}
