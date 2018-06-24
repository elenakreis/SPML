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
        TestFactory tf = new TestFactory();
        MarkovDecisionProblem mdp = tf.defaultMDP();
        QLearning ql = new QLearning(mdp,"regular");
        ql.doQL();
        
    }
}
