package a3_variableelimination;

import java.util.ArrayList;

/**
 * Main class to read in a network, add queries and observed variables, and
 * eliminate variables.
 *
 * @author Marcel de Korte, Moira Berens, Djamari Oetringer, Abdullahi Ali,
 * Leonieke van den Bulk
 */
public class Main {

    private final static String networkName = "earthquake.bif"; // The network to be read in (format from http://www.bnlearn.com/bnrepository/)

    public static void main(String[] args) {

        // Read in the network
        Networkreader reader = new Networkreader(networkName);

        // Get the variables and probabilities of the network
        ArrayList<Variable> Vs = reader.getVs();
        ArrayList<Table> Ps = reader.getPs();

        // Print variables and probabilities
        reader.printNetwork(Vs, Ps);

        // Ask user for query and heuristic
        reader.askForQuery();

        // Turn this on if you want to experiment with different heuristics for bonus points (you need to implement the heuristics yourself)
        //reader.askForHeuristic();
        //String heuristic = reader.getHeuristic();
        Variable Q = reader.getQueriedVariable();

        // Ask user for observed variables 
        reader.askForObservedVariables();
        ArrayList<Variable> O = reader.getObservedVariables();

        // Print the query and observed variables
        reader.printQueryAndObserved(Q, O);

        //PUT YOUR CODE FOR THE VARIABLE ELIMINATION ALGORITHM HERE
        varelAlgorithm(Vs, Ps, Q, O);
    }

    static void varelAlgorithm(ArrayList<Variable> Vs, ArrayList<Table> factors, Variable Q, ArrayList<Variable> O) {
        // a) done
        // b) done
        // c) done
        // d) factorizing done; 
        //    todo: reduce observed variables
        if (O.size() > 0) {
            for (Variable o : O) {
                for (Table f : factors) {
                    if (o.equals(f.getNode()) || f.getParents().contains(o)) {
                        // remove instances of wrong values
                        int posO = 0;
                        if (o.equals(f.getNode())) {
                            posO = o.getNrOfParents(); // if a node has two parents then its values are in col 2 of the table
                        } else {
                            for (int i = 0; i < f.getParents().size(); i++) {
                                if (o.equals(f.getParents().get(i))) {
                                    posO = i;
                                }
                            }
                        }
                        //posO done
                        ArrayList<ProbRow> table = f.getTable(); // does this work? removing rows from loop?
                        for (ProbRow row : table) {
                            if (!row.getValues().get(posO).equals(o.getValue())) {
                                f.getTable().remove(row);
                            }
                        }
                    }
                }
            }
        }

        // e)
        ArrayList<Variable> elimOrder = new ArrayList<>();
        ArrayList<Variable> parents = new ArrayList<>(); // make set?

        for (Variable v : Vs) {     // get a list of parents
            if (v.hasParents()) {
                parents.addAll(v.getParents());
            } else {
                elimOrder.add(v); // add top nodes
            }
        }
        System.out.println(parents);
        for (Variable v : Vs) {
            if (!parents.contains(v) && !v.equals(Q)) { //make sure you dont add the query variable
                elimOrder.add(0, v);     // add leaf nodes at front of list
            }
        }
        for (Variable v : Vs) {
            if (!elimOrder.contains(v) && !v.equals(Q)) {
                elimOrder.add(v);       // add remaining nodes (exc. query)
            }
        }

        // f)
        for (Variable z : elimOrder) {
            ArrayList<Table> zFactors = new ArrayList<>(); // list of factors that contain z
            for (Table factor : factors) {
                if (factor.getNode().equals(z) || factor.getParents().contains(z)) {
                    zFactors.add(factor);
                }
            }
            // a)
            if (zFactors.size() > 1) { // only multiply if theres more than one factor
                Table biggest = zFactors.get(0);
                for (Table zFactor : zFactors) { // find the biggest table
                    if (zFactor.size() > biggest.size()) {
                        biggest = zFactor;
                    }
                }
                for (ProbRow row : biggest.getTable()) {
                    
                }
            }
        }
    }
}
