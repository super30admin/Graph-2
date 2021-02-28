// Time, Space - O(V+E), O(V+E)
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
        for(int i=0;i<n;i++) {
            graph.add(new ArrayList<>());                        
        }
        
        for(List<Integer> edge : connections) {
            int to = edge.get(0);
            int from = edge.get(1);
            
            graph.get(to).add(from);
            graph.get(from).add(to);
        }
        
        
    }
    
    private void dfs(int v, int u, int[] discovery, int[] lowest) {
        if(discovery[v] != -1) {
            return;
        }
        
        discovery[v] = time;
        lowest[v] = time;
        time++;
        List<Integer> neighbors = graph.get(v);
        
        for(int n : neighbors) {
            if(n == u) {
                continue;
            }
            dfs(n, v, discovery, lowest);
            if(lowest[n] > discovery[v])  {
                result.add(Arrays.asList(n, v));
            }
            lowest[v] = Math.min(lowest[n], lowest[v]);
        }
        
        
        
        
    }
}
