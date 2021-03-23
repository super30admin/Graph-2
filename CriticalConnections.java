//TC: O(v+e)
//SC:O(v)

class Solution {
    
     HashMap<Integer,List<Integer>> graph;
    
    List<List<Integer>> result;
    
    int[]depthFromSource;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        
        this.graph  = new HashMap<>();
        this.result = new ArrayList<>();
        this.depthFromSource = new int[n];
        buildGraph(connections);
        dfs(0,0,1);
        return result;
    }
    
    private int dfs(int currNode, int parentNode, int depth){
        depthFromSource[currNode] = depth;
        
        for(int nextNode : graph.get(currNode)){
            
            //skip the next node if it a parent node
            if(nextNode == parentNode){
                continue; //skip it
            }
            
            if(depthFromSource[nextNode] > 0){ //means it was visited before
                //record the min of depth of currNode and depth of nextnode
                depthFromSource[currNode] = Math.min(depthFromSource[currNode],depthFromSource[nextNode]);
                
            }
            else{
                
                depthFromSource[currNode] = Math.min(depthFromSource[currNode],dfs(nextNode,currNode,depth+1));
            }
            
            
            if(depth < depthFromSource[nextNode]){
                result.add(Arrays.asList(currNode,nextNode));
            }
        }
        return depthFromSource[currNode];
    }
    
    
     private void buildGraph(List<List<Integer>> connections){
        //undirected graph
        for(List<Integer> c : connections){
            int u = c.get(0);  int v = c.get(1);
            if(!this.graph.containsKey(u)){
                this.graph.put(u, new ArrayList<>());
            }
            this.graph.get(u).add(v);
            
            if(!this.graph.containsKey(v)){
                this.graph.put(v, new ArrayList<>());
            }
            this.graph.get(v).add(u);
        }
    }
}