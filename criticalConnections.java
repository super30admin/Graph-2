// Time Complexity : O( V + E)
// Space Complexity : O(V)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;

class Main {
    // approch 1 using tarjan's algorithm
    // graph adjacency list
    private static List<List<Integer>> graph;
    // result list
    private static List<List<Integer>> result;
    // discovery time array
    private static int[] discovery;
    // lowest time to discover
    private static int[] lowest;
    private static int time = 0;

    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // null case
        if (connections == null)
            return new ArrayList<>();
        result = new ArrayList<>();
        graph = new ArrayList<>();
        discovery = new int[n];
        lowest = new int[n];
        time = 0;
        // fill discovery array with -1 to
        // get mark as visited if visited
        Arrays.fill(discovery, -1);

        // first build adjacency list for a graph
        buildGraph(connections, n);
        // dfs traversal on a graph and get critical node
        // System.out.println(graph);
        dfs(0, 0);
        return result;
    }

    // build graph
    private static void buildGraph(List<List<Integer>> connections, int n) {
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> c : connections) {
            graph.get(c.get(0)).add(c.get(1));
            graph.get(c.get(1)).add(c.get(0));
        }
    }

    // dfs traversal of the graph
    // v -> current Node
    // u -> parent Node
    private static void dfs(int v, int u) {
        // base case
        if (discovery[v] != -1)
            return;
        discovery[v] = time;
        lowest[v] = time;
        time++;

        // main logic
        // go over all the adjacent nodes
        // of particular node and traverse further
        for (int n : graph.get(v)) {
            // if n is equal to its parent we continue
            if (n == u)
                continue;
            dfs(n, v);
            // if lowest of n is greater than the discovery of
            // its parent that means it is not containing
            // any cycle as it is only explored from its parent
            if (lowest[n] > discovery[v]) {
                result.add(Arrays.asList(v, n));
            }
            // update the lowest with lowest discovery time
            lowest[v] = Math.min(lowest[n], lowest[v]);
        }

    }

    public static void main(String[] args) {

    }
}