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
public enum TestCase {
    TC1, TC2, TC3, TC4, TC5;//different world
     //different discounts
    // etc.
    
    private MarkovDecisionProblem mdp;
    
    public MarkovDecisionProblem getMDP() {
        makeMDP();
        return mdp;
    }

    private void makeMDP() {
        switch (this) {
            case TC1: // default
                mdp = new MarkovDecisionProblem();
                break;
            case TC2:
                mdp = new MarkovDecisionProblem(10, 10);
                mdp.setField(9, 9, Field.REWARD);
                mdp.setPosReward(10);
                break;
            case TC3:
                mdp = new MarkovDecisionProblem(10, 10);
                mdp.setField(9, 9, Field.REWARD);
                mdp.setField(3, 4, Field.OBSTACLE);
                mdp.setField(9, 5, Field.OBSTACLE);
                mdp.setField(4, 2, Field.OBSTACLE);
                mdp.setField(1, 8, Field.OBSTACLE);
                mdp.setField(7, 1, Field.OBSTACLE);
                mdp.setField(5, 7, Field.OBSTACLE);
                mdp.setPosReward(10);
                break;
            case TC4:
                mdp = new MarkovDecisionProblem(10, 10);
                mdp.setField(4, 4, Field.REWARD);
                mdp.setPosReward(10);
                break;
            case TC5:
                mdp = new MarkovDecisionProblem(5, 7);
                mdp.setField(4, 6, Field.REWARD);
                mdp.setField(2, 3, Field.NEGREWARD);
                mdp.setField(0, 6, Field.NEGREWARD);
                break;
            default:
                break;
        }
    }
}
