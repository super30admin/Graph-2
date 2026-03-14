// Time Complexity : O(V+E) tarjans algorithm
// Space Complexity : O(V+E) for graph and O(v) for orders
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// Tarjans Algorithm - An edge is critical if it is not part of any cycle
// In the graph, we do forward only DFS (do not return to parent ever unless the dfs is fully done on the node)
// Visiting each NEW node , we mark the discovery and lowest time stamp
// Once the DFS is completle , only nodes with lowest[n] > discovery[v] are critical connections because the lowest[n] is the earliest discovered node reachable from n (including n itself)
// It can reach the parent node only if it has a back edge to the parent node -> lowest[n] <= discovery[v] - means a cycle exist

class Solution {
    int[] discovery;
    int[] lowest;
    Map<Integer, List<Integer>> graph;
    List<List<Integer>> result;
    int time;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        discovery = new int[n];
        Arrays.fill(discovery, -1);
        lowest = new int[n];
        graph = new HashMap();
        result = new ArrayList();
        time = 0;

        for (int i = 0; i < n; i++)
            graph.put(i, new ArrayList());
        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        forwardDFS(0, 0);
        return result;
    }

    private void forwardDFS(int v, int u) {
        if (discovery[v] != -1)
            return;
        discovery[v] = time;
        lowest[v] = time;
        time++;

        for (int n : graph.get(v)) {
            if (n == u)
                continue;
            forwardDFS(n, v);
            if (lowest[n] > discovery[v]) {
                result.add(Arrays.asList(n, v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }

    }
}