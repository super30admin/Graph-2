// Time Complexity: O(E+V)
// Space Complexity: O(V) / O(n)
// Tarjans Algorithm : Strongly Connected Component, each node in cycle can reach other node
public class CriticalConnectNW {
    List<List<Integer>> result;
    List<List<Integer>> graph;
    int discovery[];
    int lowest[];
    int time;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        
        if(n == 0)
            return result;
        
        lowest = new int[n];
        discovery = new int[n];
        Arrays.fill(discovery, -1);
        
        graph = new ArrayList<>();
        buildGraph(n, connections);
        
        // tarjans algorithm
        dfs(0,0);
        
        return result;
    }
    
    private void buildGraph(int n, List<List<Integer>> connections)
    {
        // initialize graph with all nodes
        for(int i = 0 ; i < n ; i ++)
        {
            graph.add(new ArrayList<>());
        }
        
        // add bi-directional edge
        for(List<Integer> edge : connections)
        {
            int from = edge.get(0), to = edge.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }
    
    private void dfs(int node, int parent)
    {
        // base
        // to mark visited
        if(discovery[node] != -1)
            return;
     
        // logic
        // set time
        discovery[node] = time;
        lowest[node] = time;
        
        // increase 
        time++;
        
        // get neigh of nodes
        for(Integer neig: graph.get(node))
        {
            if(neig == parent) // bidirectional graph, node can have parent in its neigh
                continue;
            
            dfs(neig, node);
            
            if(discovery[node] < lowest[neig]) // same cycle
            {
                result.add(Arrays.asList(node, neig)); // node and neig
            }
            
           lowest[node] = Math.min(lowest[node], lowest[neig]); // belong to same component
        }   
    }
}
