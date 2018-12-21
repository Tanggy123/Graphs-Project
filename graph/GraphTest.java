package graph;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author Dayuan Tang
 */
public class GraphTest {

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void testRemoveAddVertex() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        g.remove(3);
        g.remove(4);
        g.remove(5);
        g.add();
        g.add();
        Integer[] ans = {1, 2, 3, 4};
        Iteration<Integer> a = g.vertices();
        for (int i = 0; i < g.vertexSize(); i++) {
            assertEquals(a.next(), ans[i]);
        }
    }

    @Test
    public void testRemoveAddEdge() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        g.add(1, 2);
        g.add(1, 4);
        g.add(2, 4);
        g.add(3, 2);
        g.add(3, 2);
        g.add(3, 1);
        g.add(2, 3);
        g.remove(2, 5);
        g.remove(2, 4);

        assertEquals(g.edgeSize(), 5);
        assertEquals(g.edgeId(2, 4), 0);
    }

    @Test
    public void directedGraph() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        g.remove(3);
        g.add();
        g.add(1, 2);
        g.add(1, 4);
        g.add(2, 4);
        g.add(3, 2);
        g.add(3, 2);
        g.add(3, 1);
        g.add(2, 3);

        Iteration<Integer> fourPre = g.predecessors(4);
        Iteration<Integer> fourSuc = g.successors(4);
        Iteration<Integer> twoSuc = g.successors(2);

        assertEquals(2, g.outDegree(3));
        assertEquals(1, g.inDegree(3));
        assertEquals((Integer) 1, fourPre.next());
        assertEquals((Integer) 2, fourPre.next());
        assertFalse(fourPre.hasNext());
        assertFalse(fourSuc.hasNext());
        assertEquals((Integer) 4, twoSuc.next());
        assertEquals((Integer) 3, twoSuc.next());
        assertFalse(twoSuc.hasNext());

        g.remove(3, 1);
        assertEquals(5, g.edgeSize());

        g.remove(3);
        assertEquals(3, g.vertexSize());
        assertEquals(3, g.edgeSize());
    }

    @Test
    public void undirectedGraph() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        g.remove(3);
        g.add();
        g.add(1, 2);
        g.add(1, 4);
        g.add(2, 4);
        g.add(3, 2);
        g.add(3, 2);
        g.add(3, 1);
        g.add(2, 3);

        assertEquals(4, g.vertexSize());
        assertEquals(5, g.edgeSize());
        assertEquals(4, g.maxVertex());

        g.remove(4);
        Iteration<Integer> threePre = g.predecessors(3);

        assertEquals(2, g.degree(3));
        assertEquals((Integer) 2, threePre.next());
        assertEquals((Integer) 1, threePre.next());
        assertFalse(threePre.hasNext());
    }

    @Test
    public void testBreadthFirst1() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        g.add(1, 2);
        g.add(1, 4);
        g.add(2, 4);
        g.add(3, 2);
        g.add(3, 2);
        g.add(3, 1);
        g.add(2, 3);
        BreadthFirstTraversal bft = new BreadthFirstTraversal(g);
        bft.traverse(1);
        assertEquals((Integer) 2, bft._marked.get(1));
        assertEquals((Integer) 4, bft._marked.get(2));
        assertEquals((Integer) 3, bft._marked.get(3));
    }

    @Test
    public void testBreadthFirst2() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 6; i++) {
            g.add();
        }
        g.add(1, 2);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 6);
        g.add(4, 3);
        BreadthFirstTraversal bft = new BreadthFirstTraversal(g);
        bft.traverse(1);
        assertEquals((Integer) 1, bft._marked.get(0));
        assertEquals((Integer) 2, bft._marked.get(1));
        assertEquals((Integer) 4, bft._marked.get(2));
        assertEquals((Integer) 5, bft._marked.get(3));
        assertEquals((Integer) 6, bft._marked.get(4));
        assertEquals((Integer) 3, bft._marked.get(5));
    }

    @Test
    public void testDepthFirst1() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 6; i++) {
            g.add();
        }
        g.add(1, 2);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 6);
        g.add(4, 3);
        DepthFirstTraversal dft = new DepthFirstTraversal(g);
        dft.traverse(1);
        assertEquals((Integer) 1, dft._marked.get(0));
        assertEquals((Integer) 2, dft._marked.get(1));
        assertEquals((Integer) 5, dft._marked.get(2));
        assertEquals((Integer) 6, dft._marked.get(3));
        assertEquals((Integer) 4, dft._marked.get(4));
        assertEquals((Integer) 3, dft._marked.get(5));
    }
    @Test
    public void testDepthFirst2() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(3, 4);
        DepthFirstTraversal dft = new DepthFirstTraversal(g);
        dft.traverse(1);
        assertEquals((Integer) 1, dft._marked.get(0));
        assertEquals((Integer) 2, dft._marked.get(1));
        assertEquals((Integer) 3, dft._marked.get(2));
        assertEquals((Integer) 4, dft._marked.get(3));
    }

    @Test
    public void testShortestPaths1() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 6; i++) {
            g.add();
        }
        g.add(1, 2);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 6);
        g.add(4, 3);
        g.add(3, 6);
        TestSimpleShortestPaths ssp1 = new TestSimpleShortestPaths(g, 1, 6);
        ssp1.setPaths();
        List<Integer> path = ssp1.pathTo();
        Integer[] ans1 = {1, 2, 6};
        for (int i = 0; i < path.size(); i++) {
            assertEquals(path.get(i), ans1[i]);
        }
        assertEquals(ssp1.getPredecessor(6), 2);
        assertEquals(ssp1.getPredecessor(2), 1);
    }

    @Test
    public void testShortestPaths2() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 6; i++) {
            g.add();
        }
        g.add(1, 2);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 6);
        g.add(4, 3);
        g.add(3, 6);
        g.add(1, 6);
        TestSimpleShortestPaths ssp2 = new TestSimpleShortestPaths(g, 1, 6);
        ssp2.setPaths();
        List<Integer> path = ssp2.pathTo();
        Integer[] ans2 = {1, 6};
        assertEquals(2, path.size());
        for (int i = 0; i < path.size(); i++) {
            assertEquals(path.get(i), ans2[i]);
        }
        assertEquals(ssp2.getPredecessor(6), 1);
        assertEquals(ssp2.getWeight(6), 1.0, 0);
    }

}
