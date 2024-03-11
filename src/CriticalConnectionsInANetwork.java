// Time Complexity:  O(V+E)
// Space Complexity: O(V)

class Solution {
    
    List<List<Integer>> result;
    List<List<Integer>> graph;
    int[] discovery;
    int[] lowest;
    int time = 0;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>(); 
        if(connections == null) 
            return result;
        graph = new ArrayList<>();
        discovery = new int[n];
        lowest = new int[n];
        Arrays.fill(discovery, -1);
        buildGraph(n, connections);
        dfs(0, 0);
        return result;
    }
    
    private void buildGraph(int n, List<List<Integer>> connections) {
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }
        for(List<Integer> list : connections) {
            graph.get(list.get(0)).add(list.get(1));
            graph.get(list.get(1)).add(list.get(0));
        }
    }
    
    private void dfs(int v, int u) {
        
        if(discovery[v] != -1)                                    // if node visited
            return;                            
        discovery[v] = time;                                      // update discovery
        lowest[v] = time;                                         // update lowest
        time++;                                                   // update time
        
        for(int n : graph.get(v)) {                               // neighbors
            if(n!=u) {                                            // if neighbor not parent
                dfs(n, v);                                        // 1) dfs on neighbor
                if(lowest[n] > discovery[v]) {                    // 2) lowest(neighbor) > discovery(curr)
                    result.add(Arrays.asList(n, v));              //    then add inn reslt
                }
                lowest[v] = Math.min(lowest[v], lowest[n]);       // 3) update lowest(neighbor) if possible
            }
        }
    }
    
}
