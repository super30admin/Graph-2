// Time Complexity : O(v + e) where v is the number of vertices and e is the number of edges
// Space Complexity : O(v + e) List of List of graph with vertices and edges
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Understanding Tarjahn's Algorithm 
/* Your code here along with comments explaining your approach: As you proceed for dfs from the lowest node id. Start the exploration from the first
id and do DFS to explore more nodes till you find a cycle, it means that after a certain point if a cycle exists then the lower value of the last explroed
node will be lesser than the discovery value of the next node. If the cycle does not exists it means that the lower value of the node will be greater
than the discovery value of the parent node. Add such nodes to critical edges. Then update the lower value of current node to be the minimum of lower
node between current and the parent node. Discovery depicts the order of the dfs from the start whereas lower is the earliest node id that can be explored
from the current node, hence if the lower value is lesser than discovery it means that it is traversing to the next node that has been already in the stack.
*/
class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> critical;
   public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
       critical = new ArrayList<>();
       graph = new ArrayList<>();
       if(connections == null || connections.size() == 0) return critical;
       int[] discovery = new int[n];                                                                                    // Array discovery
       int[] lower = new int[n];                                                                                    // Array lower
       Arrays.fill(discovery, -1);  
       buildGraph(connections, n);                                                                                          // Make the graph as list of list
       dfs(0, 0, discovery, lower);                                                                                 // Start the DFS fro 0th node id
       return critical;
   }
   private void buildGraph(List<List<Integer>> connections, int n){
       for(int i =0 ; i < n; i++){
           graph.add(new ArrayList<>());
       }
       for(List<Integer> i : connections){                                                                                  // Get the connections
           int n1 = i.get(0);   
           int n2 = i.get(1);
           graph.get(n1).add(n2);                                                                                   // Add bidirectional edges
           graph.get(n2).add(n1);
       }
   }
   int st = 0;
   private void dfs(int v, int u, int[] discovery, int[] lower){
       if(discovery[v] != -1) return;                                                                           // Reached a visited node go back
       discovery[v] = st;                                                                                       // Update discovery value to the next counter st
       lower[v] = st;                                                                                           // Update lower value to the next counter st
       st++;
       for(int i: graph.get(v)){                                                                                    // Get all connections of the current node from graph
           if(i == u) continue;
           dfs(i,v,discovery,lower);                                                                            // Explore further connnections
           if(discovery[v] < lower[i]){                                                                         // if lower of child is greater than parent
               critical.add(Arrays.asList(i,v));                                                                // Critical edge found
           }
           lower[v] = Math.min(lower[i], lower[v]);                                                                 // Keep the min lower value , backtrack to see whats the earliest node
        }
   }
}