package assignment4a;

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
        TestFactory tf = new TestFactory();
        MarkovDecisionProblem mdp = tf.world4();
        
        ValueIteration vi = new ValueIteration(mdp);
        Action[][] policy = vi.doVI();

        while(!mdp.isTerminated()){
            int x = mdp.getStateXPosition();
            int y = mdp.getStateYPosition();
            mdp.performAction(policy[x][y]);
        }
    }
}
