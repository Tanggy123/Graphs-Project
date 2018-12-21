package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayDeque;
import java.util.ArrayList;

/** Implements a depth-first traversal of a graph.  Generally, the
 *  client will extend this class, overriding the visit and
 *  postVisit methods, as desired (by default, they do nothing).
 *  @author Dayuan Tang
 */
public class DepthFirstTraversal extends Traversal {

    /** A depth-first Traversal of G. */
    protected DepthFirstTraversal(Graph G) {
        super(G, new LIFOQueue<>());
        _G = G;
    }

    @Override
    protected boolean visit(int v) {
        return super.visit(v);
    }

    @Override
    protected boolean postVisit(int v) {
        return super.postVisit(v);
    }


    @Override
    protected void processSuccessors(int v) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int s : _G.successors(v)) {
            if (processSuccessor(v, s)) {
                temp.add(s);
            }
        }
        for (int i = temp.size() - 1; i >= 0; i--) {
            _fringe.add(temp.get(i));
        }
    }

    /** LIFOQueue for depth first traversal.*/
    private static class LIFOQueue<T> extends ArrayDeque<T> {

        /** A LIFOQueue for my depth first traversal.*/
        private LIFOQueue() {
            super();
        }

        @Override
        public boolean add(T v) {
            super.addFirst(v);
            return true;
        }
    }

    /** The graph being traversed.*/
    protected final Graph _G;

}
