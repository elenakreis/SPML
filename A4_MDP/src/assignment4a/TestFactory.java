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
public class TestFactory {

    public MarkovDecisionProblem defaultMDP() {
        MarkovDecisionProblem mdp = new MarkovDecisionProblem();
        return mdp;
    }

    public MarkovDecisionProblem world1() {
        MarkovDecisionProblem mdp = new MarkovDecisionProblem(10, 10);
        mdp.setField(9, 9, Field.REWARD);
        mdp.setPosReward(10);
        return mdp;
    }

    public MarkovDecisionProblem world2() {
        MarkovDecisionProblem mdp = new MarkovDecisionProblem(10, 10);
        mdp.setField(9, 9, Field.REWARD);
        mdp.setField(3, 4, Field.OBSTACLE);
        mdp.setField(9, 5, Field.OBSTACLE);
        mdp.setField(4, 2, Field.OBSTACLE);
        mdp.setField(1, 8, Field.OBSTACLE);
        mdp.setField(7, 1, Field.OBSTACLE);
        mdp.setField(5, 7, Field.OBSTACLE);
        mdp.setPosReward(10);
        return mdp;
    }

    public MarkovDecisionProblem world3() {
        MarkovDecisionProblem mdp = new MarkovDecisionProblem(10, 10);
        mdp.setField(4, 4, Field.REWARD);
        mdp.setPosReward(10);
        return mdp;
    }

    public MarkovDecisionProblem world4() {
        MarkovDecisionProblem mdp = new MarkovDecisionProblem(5, 7);
        mdp.setField(4, 6, Field.REWARD);
        mdp.setField(2, 3, Field.NEGREWARD);
        mdp.setField(0, 6, Field.NEGREWARD);
        return mdp;
    }
    
    public MarkovDecisionProblem discount1(){
        MarkovDecisionProblem mdp = new MarkovDecisionProblem();
        mdp.setGAMMA(0.9);
        return mdp;
    }
    
    public MarkovDecisionProblem discount2(){
        MarkovDecisionProblem mdp = new MarkovDecisionProblem();
        mdp.setGAMMA(0.1);
        return mdp;
    }
    
    public MarkovDecisionProblem statePenalty1(){
        MarkovDecisionProblem mdp = new MarkovDecisionProblem();
        mdp.setNoReward(0);
        return mdp;
    }
    
    public MarkovDecisionProblem statePenalty2(){
        MarkovDecisionProblem mdp = new MarkovDecisionProblem();
        mdp.setNoReward(-0.01);
        return mdp;
    }
    
    public MarkovDecisionProblem statePenalty3(){
        MarkovDecisionProblem mdp = new MarkovDecisionProblem();
        mdp.setNoReward(-0.1);
        return mdp;
    }
    
    public MarkovDecisionProblem transitionProb1(){
        MarkovDecisionProblem mdp = new MarkovDecisionProblem();
        mdp.setDeterministic();
        return mdp;
    }
    
    public MarkovDecisionProblem transitionProb2(){
        MarkovDecisionProblem mdp = new MarkovDecisionProblem();
        mdp.setProbsStep(0.5, 0.5, 0, 0);
        return mdp;
    }
    

}
