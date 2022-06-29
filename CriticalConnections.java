// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

class Solution {
    List<List<Integer>> result;
    List<List<Integer>> graph;
    int[] lowest;
    int[] discovery;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        if(connections == null || connections.size() == 0) {
            return new ArrayList<>();
        }
                
        result = new ArrayList<>();
        graph = new ArrayList<>();
        lowest = new int[n];
        discovery = new int[n];
        time = 0;
        
        Arrays.fill(discovery, -1);
        
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        
        buildGraph(connections);
        dfs(0, 0);
        
        return result;
    }
    
    private void buildGraph(List<List<Integer>> connections) {
        
        for(List<Integer> connection: connections) {
            int from = connection.get(0);
            int to = connection.get(1);
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }
    
    private void dfs(int v, int u) {
        // base case
        if(discovery[v] != -1) {
            return;
        }
        
        // logic
        discovery[v] = time;
        lowest[v] = time;
        time++;
        
        for(int n: graph.get(v)) {
            if(n == u) {
                continue;
            }
            
            dfs(n, v);
            
            if(lowest[n] > discovery[v]) {
                result.add(Arrays.asList(n, v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }
}