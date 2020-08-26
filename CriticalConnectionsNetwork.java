// Time Complexity : O(V + E) --> where V is the number of vertices and E is the number of edges of the graph
// Space Complexity : O(V * E)
// Did this code successfully run on Leetcode (1192): Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    List<List<Integer>> result;
    List<List<Integer>> graph; // adjacency list
    int m; // number of servers
    int discovery[]; int lower[];
    int time; // 0
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.m = n;
        this.result = new ArrayList<>();
        this.graph = new ArrayList<>();
        this.discovery = new int[m];
        this.lower = new int[m];
        
        Arrays.fill(discovery, -1);
        buildGraph(connections);
        dfs(1, 1);
        
        return result;
    }
    
    private void buildGraph(List<List<Integer>> connections) {
        for (int i = 0; i < m; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (List<Integer> edge : connections) {
            int node1 = edge.get(0);
            int node2 = edge.get(1);
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
    }
    
    // used for populating result
    private void dfs(int v, int u) {
         // base case
        if (discovery[v] != -1) return;
        
        // logic
        discovery[v] = time;
        lower[v] = time;
        time++;
        
        for (int n : graph.get(v)) {
            if (n == u) continue;
            dfs(n, v);
            if (lower[n] > discovery[v]) {
                result.add(Arrays.asList(n, v)); // add that edge in result because thats a critical edge
            }
            lower[v] = Math.min(lower[v], lower[n]); 
        }
    }
}