    /*  Explanation
    # Leetcode problem link : https://leetcode.com/problems/critical-connections-in-a-network/
    Time Complexity for operators : o(V+E) .. V - vertex, E - edges
    Extra Space Complexity for operators : o(n) .. no o elements
    Did this code successfully run on Leetcode : NA
    Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
        # Basic approach : 
        # Optimized approach: 
                              
            # 1. 
                    A) 


class Solution {
    
    List<List<Integer>> graph;
    List<List<Integer>> result;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        result = new ArrayList<>();
        
        int[] discovery = new int [n];
        int[] lower =new int[n];
        
        Arrays.fill(discovery, -1);
        
        // build graph
        buildGraph(n, connections);
        
        // dfs on graph
        dfs(0, 0, discovery, lower, 0);
        
        return result;
    }
    
    private void buildGraph(int n, List<List<Integer>> connections){
        
        for(int x =0; x< n; x++){
            graph.add(new ArrayList<>());
        }
        
        for(List<Integer> edge :  connections){
            int source = edge.get(0);
            int dest = edge.get(1);
            
            graph.get(source).add(dest);
            graph.get(dest).add(source);
        }
    }
    
    private void dfs(int curr_v, int parent, int[] discovery, int[] lower, int time){
        // base
        if(discovery[curr_v] != -1)
            return;
        
        discovery[curr_v] = time;
        
        lower[curr_v] = time;
        time += 1;
        
        List<Integer> neighbours = graph.get(curr_v);
        
        for(int n : neighbours){
            if(n == parent)
                continue;
            
            dfs(n, curr_v, discovery, lower, time);
            
            if(lower[n] > discovery[curr_v]){
                result.add(Arrays.asList(n, curr_v));
            }
            
            lower[curr_v] = Math.min(lower[curr_v], lower[n]);
        }
    }
}