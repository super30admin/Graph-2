
//TC: O(VE) , where V and E are vertexes and edges respectively
//SC : O(VE) , where V and E are vertexes and edges respectively
class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        result = new ArrayList<>();
        int[] discovery = new int[n];
        int[] lowest = new int[n];
        Arrays.fill(discovery,-1);
       
        buildGraph(n,connections);
         dfs(0,0,discovery,lowest);
        return result;
        
        
    }
    
    private void buildGraph(int n,List<List<Integer>> connections ){
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
        
        for(List<Integer> edge : connections){
            int from = edge.get(0), to = edge.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }
    //Algo
    /*
    Base case : if that node has already been visited
    Logic case:
    fill in that node's discovery and latest time
    Iterate on all its edges:
        if the edge happens to be a parent, continue;
        if not do a dfs on it.
        once all that node's edges has been discovered,check if lowest[node] > discovery[parent].
            if yes, add it to result;
        Make the lowest of parent to be minimum of the two nodes.

    */
    private void dfs(int u,int prev,int[] discovery, int[] lowest){
        //Base Case
        if(discovery[u]!=-1) return;
        
        //Logic;
        discovery[u] = time;
        lowest[u] = time;
        time++;
        List<Integer> edges = graph.get(u);
        
        for(Integer v : edges){
            if(v == prev) continue;
            dfs(v,u,discovery,lowest);
            if(lowest[v] > discovery[u]){
                result.add(Arrays.asList(u,v));
            }
            lowest[u] = Math.min(lowest[u],lowest[v]);
        }
        
    }
}