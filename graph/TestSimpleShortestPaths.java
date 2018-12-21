package graph;

class TestSimpleShortestPaths extends SimpleShortestPaths {

    TestSimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
    }

    TestSimpleShortestPaths(Graph G, int source) {
        super(G, source);
    }

    @Override
    public double getWeight(int u, int v) {
        return 1.0;
    }
}
