/*


https://leetcode.com/problems/critical-connections-in-a-network
Did it run on leetcode: yes
Did you face any problem: Yes

Time Complexity: 0(N)
Space Complexity: 0(N)

Algorithm:
- Maintain two arrays called discoveryTime[] and lowerTime[] which represents time to discover node i and lowest time
to discover node i respectively.

- Whenever discoveryTime[node]<lowestTime[neighbor of node] it tells us there is a back edge ie there is another parh apart
from node to reach its neighbor this means there is a cycle.

- Whenever you encounter such edges it is a critical connection.



*/


class Solution {
    
    int[] lowerTime;
    int[] discoverTime;
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int time;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        discoverTime = new int[n];
        lowerTime = new int[n];
        graph = new ArrayList<List<Integer>>();
        result = new ArrayList<List<Integer>>();
        
        Arrays.fill(discoverTime,-1);
        createGraph(n,connections);
        
        dfsHelper(0,0);
        
        return result;
        
    }
    
    
    private void dfsHelper(int node, int parent){
        
        discoverTime[node]=time;
        lowerTime[node]=time;
        
        ++time;
        for(int neigh : graph.get(node)){
            if(neigh==parent){ continue; }
            if(discoverTime[neigh]==-1){
                dfsHelper(neigh,node);
                if(lowerTime[neigh]>discoverTime[node]){
                    // cycle exists
                    // neighbor can be reached from other path as well
                    result.add(Arrays.asList(neigh,node));
                } 
            }
            
            lowerTime[node] = Math.min(lowerTime[node],lowerTime[neigh]);
        }
    } 
    
    private void createGraph(int n,List<List<Integer>> connections){
        
        for(int i=0;i<n;++i){
            graph.add(new ArrayList<Integer>());
        }
        
        connections.forEach((connection)->{
            Integer u = connection.get(0);
            Integer v = connection.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        });
    }
    
}