public class CriticalConn {
    
    // Time Complexity : O(V+E) where V is vertices of graph and E is edges
    // Space Complexity : O(V+E)
    // Did this code successfully run on Leetcode : Yes
    // Any problem you faced while coding this : No


    List<List<Integer>> result;
    List<List<Integer>> graph;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        graph = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(List<Integer> c : connections){
            graph.get(c.get(0)).add(c.get(1));
            graph.get(c.get(1)).add(c.get(0));
        }
        
        int[] discovered = new int[n];
        Arrays.fill(discovered, -1);
        
        int[] lower = new int[n];
        
        dfs(discovered, lower, 0, 0);
        
        return result;
    }
    
    private void dfs(int[] discovered, int[] lower, int child, int parent){
        
        //base
        if(discovered[child] != -1)
            return;
        //logic
        
        discovered[child] = time;
        lower[child] = time;
        time++;
        
        for(int neighbour : graph.get(child)){
            if(neighbour == parent)
                continue;
            dfs(discovered, lower, neighbour, child);
            
            if(discovered[child] < lower[neighbour] ){
                result.add(Arrays.asList(child, neighbour));
            }
            lower[child] = Math.min(lower[child], lower[neighbour]);
        }
        
    }

}