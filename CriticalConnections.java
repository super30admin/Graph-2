// Time Complexity : O(V+E)
// Space Complexity : O(V^2)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// We use dfs to solve this
// we build an adjacency matrix
// We then use Strassen's Algorithm
// we will then calucalate the minimum spread

class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int[] discovery;
    int[] lowest;
    int time;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        if (n == 0)
            return new ArrayList<>();

        discovery = new int[n];
        Arrays.fill(discovery, -1);
        lowest = new int[n];
        graph = new ArrayList<>();
        result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        buildGraph(connections);
        dfs(0, 0);
        return result;

    }

    private void buildGraph(List<List<Integer>> connections) {
        for (List<Integer> edge : connections) {
            int from = edge.get(0);
            int to = edge.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }

    private void dfs(int v, int u) {
        if (discovery[v] != -1)
            return;
        discovery[v] = time;
        lowest[v] = time;
        time++;
        List<Integer> children = graph.get(v);

        if (children != null) {
            for (Integer n : children) {
                if (n == u)
                    continue;
                dfs(n, v);
                if (lowest[n] > discovery[v]) {
                    result.add(Arrays.asList(n, v));
                }
                lowest[v] = Math.min(lowest[n], lowest[v]);
            }
        }
    }
}