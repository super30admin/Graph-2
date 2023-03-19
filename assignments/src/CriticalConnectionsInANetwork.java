import java.util.*;

// Approach: Tarjan's algorithm: cycle finding algorithm using DFS
// 1. discovery[]: natural order of discovery in DFS
// 2. lowest[]: earliest discovered node that I can reach from current node
// 3. if (lowest[n] > discovery[v]) add the pair/edge (v,n) to result list
// Time: O(V+E)
// Space: O(V+E)

class CriticalConnectionsInANetwork {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int[] discovery;
    int[] lowest;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        if (connections == null) return result;
        graph = new ArrayList<>();
        discovery = new int[n];
        Arrays.fill(discovery, -1);
        lowest = new int[n];
        buildGraph(connections, n);
        dfs(0,0);
        return result;
    }
    private void buildGraph(List<List<Integer>> connections, int n) {
        for (int i = 0; i<n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : connections) {
            int n1 = edge.get(0);
            int n2 = edge.get(1);
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
    }
    private void dfs(int v, int u) {
        // base case
        if (discovery[v] != -1) return;
        // logic
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for (int n : graph.get(v)) {
            if (n == u) continue;       // if n = parent node (where we came from); continue
            dfs(n,v);
            if (lowest[n] > discovery[v]) {
                result.add(Arrays.asList(n,v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }
}