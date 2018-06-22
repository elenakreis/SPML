package assignment4a;

import java.util.Arrays;

/**
 * This main is for testing purposes (and to show you how to use the MDP class).
 *
 * @author Jered Vroon
 *
 */
public class Main {

    /**
     * @param args, not used
     */
    public static void main(String[] args) {
        
        MarkovDecisionProblem mdp = new MarkovDecisionProblem();
        mdp.setInitialState(0, 0);
        ValueIteration vi = new ValueIteration(mdp);
        Action[][] policy = vi.doVI();
        System.out.println("Variable iteration done!");
        printArray(policy);
        while(!mdp.isTerminated()){
            int x = mdp.getStateXPosition();
            int y = mdp.getStateYPostion();
            mdp.performAction(policy[x][y]);
        }
        /*
		MarkovDecisionProblem mdp = new MarkovDecisionProblem();
		mdp.setInitialState(0, 0);
		for (int i = 0; i < 15; i++){
			mdp.performAction(Action.UP);
			mdp.performAction(Action.UP);
			mdp.performAction(Action.RIGHT);
			mdp.performAction(Action.RIGHT);
			mdp.performAction(Action.RIGHT);
			mdp.restart();
		}
		
		MarkovDecisionProblem mdp2 = new MarkovDecisionProblem(10, 10);
		mdp2.setField(5, 5, Field.REWARD);
		for (int i = 0; i < 100; i++){
			mdp2.performAction(Action.UP);
			mdp2.performAction(Action.RIGHT);
			mdp2.performAction(Action.DOWN);
			mdp2.performAction(Action.LEFT);
		}
         */
    }
    
    private static void printArray(Action[][] array){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(array[j][i]+" ");
            }
            System.out.println("");
        }
    }
}
