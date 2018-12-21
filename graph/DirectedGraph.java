package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Dayuan Tang
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        int numIn = 0;
        for (int[] e : edges) {
            if (e[1] == v) {
                numIn += 1;
            }
        }
        return numIn;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        ArrayList<Integer> predecessors = new ArrayList<>();
        for (int[] e : edges) {
            if (e[1] == v) {
                predecessors.add(e[0]);
            }
        }
        return Iteration.iteration(predecessors);
    }

}
