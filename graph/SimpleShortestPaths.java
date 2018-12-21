package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author Dayuan Tang
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        for (int i = 0; i < G.vertexSize() + 1; i++) {
            _vWeight.add(Double.MAX_VALUE);
            _predecessor.add(0);
        }
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    @Override
    protected abstract double getWeight(int u, int v);

    @Override
    public double getWeight(int v) {
        return _vWeight.get(v);
    }

    @Override
    protected void setWeight(int v, double w) {
        _vWeight.set(v, w);
    }

    @Override
    public int getPredecessor(int v) {
        return _predecessor.get(v);
    }

    @Override
    protected void setPredecessor(int v, int u) {
        _predecessor.set(v, u);
    }

    /** Mapping vertices to their weight.*/
    private final ArrayList<Double> _vWeight = new ArrayList<>();

    /** Mapping vertices to their predecessor.*/
    private final ArrayList<Integer> _predecessor = new ArrayList<>();

}
