// TC O(E*(E+V)) SC O(E)
class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        result = new ArrayList<>();
        buildGraph(connections, n);
        int[] discovery = new int[n];
        Arrays.fill(discovery, -1);
        int[] lowest = new int[n];
        dfs(0, 0, discovery, lowest);
        return result;
    }
    
    private void buildGraph(List<List<Integer>> connections, int n) {
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer>edge : connections) {
            int to = edge.get(0);
            int from = edge.get(1);
            graph.get(to).add(from);
            graph.get(from).add(to);
        }
    }
    
    private void dfs(int u, int prev, int[] discovery, int [] lowest){
        if (discovery[u] != -1){
            return;
        }
        discovery[u] = time;
        lowest[u] = time;
        time++;
        List<Integer> neighbors = graph.get(u);
        for (int n : neighbors) {
            if (n == prev) {
                continue;
            }
            dfs(n, u, discovery, lowest);
            if (lowest[n] > discovery[u]) {
                result.add(Arrays.asList(u, n));
            }
            lowest[u] = Math.min(lowest[n], lowest[u]);
        }
    }
}
