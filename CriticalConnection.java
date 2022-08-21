//TC,SC - O(V+E)
class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int[] lowest;
    int[] discovery;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        graph = new ArrayList<>();
        result = new ArrayList<>();
        if(connections == null) return null;
        lowest = new int[n];
        discovery = new int[n]; 
        Arrays.fill(discovery, -1);
        createGraph(connections, n); 
        
        dfs(0,0); // Start from node 0, its parent is 0
            
        return result;
    }
    public void createGraph(List<List<Integer>> connections, int n){
        for(int i = 0; i< n; i++){
            graph.add(new ArrayList<>());
        }
       
        for(List<Integer> edge : connections){
            graph.get(edge.get(0)).add(n2);
            graph.get(edge.get(1)).add(n1);
        }
    }
    public void dfs(int v, int u){
        if(discovery[v] != -1)  return;
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for(Integer i : graph.get(v)){
            if(i == u)  continue;
            
            dfs(i, v);  // I is child of v
            
            if(lowest[i] > discovery[v])
                result.add(Arrays.asList(i,v));
            
            lowest[v] = Math.min( lowest[v],  lowest[i]);
        }
        
        
    }
}
