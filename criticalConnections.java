// Time Complexity : O(v+e)
// Space Complexity : O(e) 
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : NO

// Your code here along with comments explaining your approach
class Solution {
    
    List<List<Integer>> ans;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        List<Integer>[] graph = constructGraph(n, connections);
        
        ans = new ArrayList();
        boolean[] visited = new boolean[n];   // flag to show if we're reached a vertex or not
        int[] lowLink = new int[n];    // the earliest moment that vertexes can travel back to
        
        dfs(graph, visited, lowLink, ans, 0, -1, 0);
        
        return ans;
        
    }
    
    private void dfs(List<Integer>[] graph, boolean[] vis, int[] low, List<List<Integer>> ans, int time, int prev, int curr) {
        
        vis[curr] = true;
        low[curr] = time;
        
        for(int n : graph[curr]) {
            
            if(n==prev) continue;
            
            if(!vis[n])
                dfs(graph, vis, low, ans, time+1, curr, n);
            
            low[curr] = Math.min(low[curr], low[n]);
            
            if(low[n] >= time + 1) {
                ans.add(Arrays.asList(curr, n));
            }
            
        }
        
    }
    
    List<Integer>[] constructGraph(int n, List<List<Integer>> connections) {
        
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        
        for(List<Integer> conn : connections) {
            
            g[conn.get(0)].add(conn.get(1));
            g[conn.get(1)].add(conn.get(0));
            
        }
        
        return g;
    }
    
}
