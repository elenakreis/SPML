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
        Table distribution = varelAlgorithm(Vs, Ps, Q, O);
        System.out.println("DONE");
        System.out.println(distribution);
    }

    private static Table varelAlgorithm(ArrayList<Variable> Vs, ArrayList<Table> factors, Variable Q, ArrayList<Variable> O) {
        // a) done
        // b) done
        // c) done
        // d) factorizing done;
        //    todo: reduce observed variables
        reduceObserved(O, factors);

        // e)
        ArrayList<Variable> elimOrder = eliminationOrder(Vs, Q);

        // f)
        eliminateVariables(elimOrder, factors);

        // g) 
        multiplyFactors(factors, Q, factors); // multiply factors with only query; can we use this?
        Table distribution = normalize(factors);
        return distribution;
    }

    private static Table normalize(ArrayList<Table> factors) {
        Table finalFactor = factors.get(0);
        double marginal = 0;
        for (ProbRow row : finalFactor.getTable()) { // calculate marginal
            marginal += row.getProb();
        }
        for (ProbRow row : finalFactor.getTable()) { // normalize
            row.setProb(row.getProb() / marginal);
        }
        return finalFactor;
    }

    private static void eliminateVariables(ArrayList<Variable> elimOrder, ArrayList<Table> factors) {
        for (Variable z : elimOrder) {
            ArrayList<Table> zFactors = new ArrayList<>(); // list of factors that contain z
            for (Table factor : factors) {
                if (factor.getNode().equals(z) || factor.getParents().contains(z)) {
                    zFactors.add(factor);
                }
            }
            // a)
            if (zFactors.size() > 1) {
                // only multiply if theres more than one factor
                multiplyFactors(zFactors, z, factors);
            }
            // b) 
            Table newFactor = zFactors.get(0); // the only one left
            int zIndex = getColIndex(newFactor, z);
            for (ProbRow row : newFactor.getTable()) { // remove the column with z
                row.getValues().remove(zIndex);
            }
            ArrayList<ProbRow> remove = new ArrayList<>();
            for (ProbRow row : newFactor.getTable()) {
                for (ProbRow row2 : newFactor.getTable()) {
                    if (!row.equals(row2)) {
                        if (row.getValues().equals(row2.getValues())) { //implement this equals
                            row.setProb(row.getProb() + row2.getProb());
                            remove.add(row2);
                        }
                    }
                }
            }
            newFactor.getTable().removeAll(remove); // remove duplicate rows

            // c) is already done
        }
    }

    private static void multiplyFactors(ArrayList<Table> zFactors, Variable z, ArrayList<Table> factors) {
        Table biggest = zFactors.get(0);
        for (Table zFactor : zFactors) { // find the biggest table
            if (zFactor.size() > biggest.size()) {
                biggest = zFactor;
            }
        }
        int zIndex = getColIndex(biggest, z);

        for (ProbRow row : biggest.getTable()) {
            String zValue = row.getValues().get(zIndex);
            double prob = row.getProb();
            for (Table zFactor : zFactors) {
                if (!zFactor.equals(biggest)) { // does equal work for tables??
                    int zIndex2 = getColIndex(zFactor, z);
                    for (ProbRow row2 : zFactor.getTable()) {
                        if (row2.getValues().get(zIndex2).equals(zValue)) {
                            prob *= row2.getProb();
                            break; // other tables might be large too??
                        }
                    }
                }
            }
            row.setProb(prob);
        }
        ArrayList<Table> remove = new ArrayList<>();
        for (Table zFactor : zFactors) {
            if (!zFactor.equals(biggest)) {
                remove.add(zFactor);
            }
        }
        factors.removeAll(remove);
        zFactors.removeAll(remove);
    }

    private static void reduceObserved(ArrayList<Variable> O, ArrayList<Table> factors) {
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
    }

    private static ArrayList<Variable> eliminationOrder(ArrayList<Variable> Vs, Variable Q) {
        ArrayList<Variable> elimOrder = new ArrayList<>();
        ArrayList<Variable> parents = new ArrayList<>(); // make set?
        for (Variable v : Vs) {     // get a list of parents
            if (v.hasParents()) {
                parents.addAll(v.getParents());
            } else {
                elimOrder.add(v); // add top nodes
            }
        }
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
        return elimOrder;
    }

    private static int getColIndex(Table table, Variable z) {
        int zIndex;
        if (table.getNode().equals(z)) {
            zIndex = table.getNrParents();
        } else {
            zIndex = table.getParents().indexOf(z);
        }
        return zIndex;
    }
}
