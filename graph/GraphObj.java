package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.Collections;

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Dayuan Tang
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public int vertexSize() {
        return vertices.size();
    }

    @Override
    public int maxVertex() {
        if (vertices.isEmpty()) {
            return 0;
        } else {
            int maxV = -Integer.MAX_VALUE;
            for (int vertex : vertices) {
                if (vertex > maxV) {
                    maxV = vertex;
                }
            }
            return maxV;
        }
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (!contains(v)) {
            return 0;
        } else {
            int numOut = 0;
            if (isDirected()) {
                for (int[] e : edges) {
                    if (e[0] == v) {
                        numOut += 1;
                    }
                }
            } else {
                for (int[] e : edges) {
                    if (e[0] == v || e[1] == v) {
                        numOut += 1;
                    }
                }
            }
            return numOut;
        }
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return vertices.contains(u);
    }

    @Override
    public boolean contains(int u, int v) {
        return contains(u) && contains(v) && edgeId(u, v) != 0;
    }

    @Override
    public int add() {
        int vertex = 1;
        while (contains(vertex)) {
            vertex += 1;
        }
        vertices.add(vertex);
        Collections.sort(vertices);
        return vertex;
    }

    @Override
    public int add(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        if (edgeId(u, v) != 0) {
            return edgeId(u, v);
        } else {
            int[] newEdge = {u, v};
            edges.add(newEdge);
            return edgeId(u, v);
        }
    }

    @Override
    public void remove(int v) {
        if (!vertices.contains(v)) {
            return;
        }
        vertices.remove((Integer) v);
        ArrayList<int[]> removeLst = new ArrayList<>();
        for (int[] e : edges) {
            if (e[0] == v || e[1] == v) {
                removeLst.add(e);
            }
        }
        for (int[] e : removeLst) {
            edges.remove(e);
        }
    }

    @Override
    public void remove(int u, int v) {
        if (edgeId(u, v) == 0) {
            return;
        }
        edges.remove(edgeId(u, v) - 1);
    }

    @Override
    public Iteration<Integer> vertices() {
        return Iteration.iteration(vertices);
    }

    @Override
    public Iteration<Integer> successors(int v) {
        ArrayList<Integer> successors = new ArrayList<>();
        if (isDirected()) {
            for (int[] e : edges) {
                if (e[0] == v) {
                    successors.add(e[1]);
                }
            }
        } else {
            for (int[] e : edges) {
                if (e[0] == v) {
                    successors.add(e[1]);
                } else if (e[1] == v) {
                    successors.add(e[0]);
                }
            }
        }
        return Iteration.iteration(successors);
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        return Iteration.iteration(edges);
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new IllegalArgumentException("vertex not from Graph");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        if (isDirected()) {
            for (int[] e : edges) {
                if (e[0] == u && e[1] == v) {
                    return edges.indexOf(e) + 1;
                }
            }
        } else {
            for (int[] e : edges) {
                if ((e[0] == u && e[1] == v)
                        || (e[0] == v && e[1] == u)) {
                    return edges.indexOf(e) + 1;
                }
            }
        }
        return 0;
    }

    /** Stores vertices of the graph.*/
    protected ArrayList<Integer> vertices;

    /** Stores edges of the graph.*/
    protected ArrayList<int[]> edges;

}
