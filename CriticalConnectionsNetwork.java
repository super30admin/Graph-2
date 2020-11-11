// Time Complexity : O(V(V+E))
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
// Tarjan's algorithm.
class Solution {
    
    // Results sets
    private List<List<Integer>> result = new ArrayList<>();
    private List<List<Integer>> graph = new ArrayList<>();
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        int[] discovery = new int[n];
        int[] lower = new int[n];
        
        Arrays.fill(discovery, -1);
        
        // Build Graph
        buildGraph(n, connections);
        
        // dfs
        dfs(0, 0, discovery, lower, 0); 
        
        return result;
    }
    
    private void buildGraph(int n, List<List<Integer>> connections){
        for(int i=0; i<n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(List<Integer> edge : connections){
            int src = edge.get(0);
            int dest = edge.get(1);
            
            graph.get(src).add(dest);
            graph.get(dest).add(src); 
        }  
    }
    
    private void dfs(int curr_v, int parent, int[] discovery, int[] lower, int time){
        //base case
        if(discovery[curr_v] != -1){
            return;
        }
        
        discovery[curr_v] = time;
        lower[curr_v] = time;
        time++;
        
        List<Integer> neighbours = graph.get(curr_v);
        
        for(int n: neighbours){
            if(n==parent){
                continue;
            }
            
            dfs(n, curr_v, discovery, lower, time);
            
            if(lower[n] > discovery[curr_v]){
                result.add(Arrays.asList(n, curr_v));
            }
            
            lower[curr_v] = Math.min(lower[curr_v], lower[n]);
        }
    }
}
