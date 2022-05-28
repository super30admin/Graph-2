//Tc : O(|V| + |E|)   No. of nodes and edges
//Sc : O(|E|) -- No. of edges

class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> list;
    int[] minimum;
    int[] discovery;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        graph = new ArrayList<>();
        list = new ArrayList<>();
        minimum = new int[n];
        discovery = new int[n]; 
        Arrays.fill(discovery, -1); // Initialized discovery array
        createGraph(connections, n); 
        
        dfs(0,0); // Start from node 0, its parent is 0
            
        return list;
    }
    public void createGraph(List<List<Integer>> connections, int n){
        for(int i = 0; i< n; i++){
            graph.add(new ArrayList<>());
        }
        //Creating edges of nodes in a Network
        for(List<Integer> edge : connections){
            int n1 = edge.get(0);
            int n2 = edge.get(1);
            
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
    }
    public void dfs(int v, int u){
        if(discovery[v] != -1)  return;
        
        discovery[v] = time;
        minimum[v] = time;
        
        time++;
        
        for(Integer i : graph.get(v)){
            if(i == u)  continue;
            
            dfs(i, v);  // I is child of v
            
            if(minimum[i] > discovery[v])
                list.add(Arrays.asList(i,v));
            
            minimum[v] = Math.min( minimum[v],  minimum[i]);
        }
        
        
    }
}