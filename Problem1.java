//Time Complexity: O(V + E)
//Space Complexity: O(V + E)
//Code run successfully on LeetCode.

public class Problem1 {

    int[] discovery;
    int[] lowest;
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int time;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        graph = new ArrayList<>();
        result = new ArrayList<>();
        discovery = new int[n];
        lowest = new int[n];
        
        Arrays.fill(discovery, -1);
        
        for(int i =0; i <n; i++)
            graph.add(new ArrayList<>());
        
        buildGraph(connections);
        dfs(0,0);
        return result;
    }
    
    private void buildGraph(List<List<Integer>> connections)
    {
        for(List<Integer> list : connections)
        {
            int from = list.get(0);
            int to = list.get(1);
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }
    
    private void dfs(int v, int u)
    {
        if(discovery[v] != -1)
            return;
        
        discovery[v] = time;
        lowest[v] = time;
        time++;
        
        for(int n : graph.get(v))
        {
            if(n == u)
                continue;
            
            dfs(n,v);
            
            if(lowest[n] > discovery[v])
            {
                result.add(Arrays.asList(n,v));
            }
            
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }
}
