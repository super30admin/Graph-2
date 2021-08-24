// Time Complexity : O(V + E)
// Space Complexity : O(V + E)

class Solution {
    List<List<Integer>> res;
    List<List<Integer>> graph;
    int[] discovery;
    int[] lowest;
    int time;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        res = new ArrayList<>();
        graph = new ArrayList<>();
        
        if(connections == null)
            return res;
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        
        discovery = new int[n];
        lowest = new int[n];
        
        Arrays.fill(discovery, -1);
            
        buildGraph(connections);
        
        dfs(0, 0);
        
        return res;
    }
    
    private void buildGraph(List<List<Integer>> connections){
        for(List<Integer> li: connections){
            int node1 = li.get(0);
            int node2 = li.get(1);
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
    }
    
    private void dfs(int v, int u){
        //base
        if(discovery[v] != -1)
            return;
        //logic
        discovery[v] = time;
        lowest[v] = time;
        time++;
        
        for(int n: graph.get(v)){
            if(n == u)
                continue;
            dfs(n, v);
            if(lowest[n] > discovery[v])
                res.add(Arrays.asList(n, v));
            lowest[v] = Math.min(lowest[n], lowest[v]);
        }
    }
}