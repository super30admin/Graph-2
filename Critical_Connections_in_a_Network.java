class Solution {
    
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        result = new ArrayList<>();
        buildgraph(connections, n);
        
        int [] discovery = new int[n];
        int [] lowest = new int[n];
        Arrays.fill(discovery, -1);
        dfs(0, 0, discovery, lowest);
        return result;
    }
    
    private void buildgraph(List<List<Integer>> connections, int n){
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(List<Integer> edge : connections){
            int in = edge.get(0);
            int out = edge.get(1);
            graph.get(in).add(out);
            graph.get(out).add(in);
        }
    }
    
    private void dfs(int v, int u, int [] discovery, int [] lowest){
        
        //base
        if(discovery[v] != -1) return;
        
        //logic
        discovery[v] = time;
        lowest[v] = time;
        time++;
        
        for(int n : graph.get(v)){
            if( n == u) continue;
            dfs(n, v, discovery, lowest);
            if(lowest[n] > discovery[v]){
                result.add(Arrays.asList(n, v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }
}


//TC: O(V + E)
