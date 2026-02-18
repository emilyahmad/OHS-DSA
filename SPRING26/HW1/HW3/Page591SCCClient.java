import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Page591SCCClient {

    private static Digraph digraphNoMultiEdges(int V, int[][] edges) {
        Digraph G = new Digraph(V);
        Set<Long> seen = new HashSet<>();

        for (int[] e : edges) {
            int v = e[0];
            int w = e[1];
            long key = (((long) v) << 32) ^ (w & 0xffffffffL);
            if (seen.add(key))
                G.addEdge(v, w);
        }
        return G;
    }

    private static void printSCCs(Digraph G) {
        KosarajuSharirSCC scc = new KosarajuSharirSCC(G);
        int m = scc.count();
        StdOut.println("SCC count = " + m);

        List<List<Integer>> comps = new ArrayList<>();
        for (int i = 0; i < m; i++)
            comps.add(new ArrayList<>());

        for (int v = 0; v < G.V(); v++) {
            comps.get(scc.id(v)).add(v);
        }

        for (int id = 0; id < m; id++) {
            StdOut.print("component " + id + ": ");
            for (int v : comps.get(id))
                StdOut.print(v + " ");
            StdOut.println();
        }
    }

    private static void runTest(String name, int V, int[][] edges, int expectedCount) {
        StdOut.println("==== " + name + " ====");
        Digraph G = digraphNoMultiEdges(V, edges);
        KosarajuSharirSCC scc = new KosarajuSharirSCC(G);

        StdOut.println("computed = " + scc.count() + ", expected = " + expectedCount
                + " -> " + (scc.count() == expectedCount ? "PASS" : "FAIL"));

        printSCCs(G);
        StdOut.println();
    }

    public static void main(String[] args) {

        int[][] test1 = {
                { 0, 1 }, { 1, 2 }, { 2, 0 },
                { 2, 3 },
                { 3, 4 }, { 4, 5 }, { 5, 3 }
        };
        runTest("Test 1", 6, test1, 2);

        int[][] test2 = {
                { 0, 1 }, { 0, 1 },
                { 1, 0 },
                { 1, 2 },
                { 2, 3 },
                { 3, 2 },
                { 3, 4 }
        };
        runTest("Test 2 (duplicates)", 5, test2, 3);
    }
}
