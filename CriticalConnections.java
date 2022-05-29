import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Time Complexity : O(V+E)
//Space Complexity : O(V)
public class CriticalConnections { 
	/**Approach: DFS**/
	int[] discovery;
    int[] lowest;  
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    	List<List<Integer>> res = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>(); ;//adjacency list
        lowest = new int[n];
        discovery = new int[n];
        Arrays.fill(discovery, -1);
        //build adjacency list;
        for(int i=0; i<n; i++){
            graph.add(new ArrayList<>()); //[],[],[],[],[]
        }
        for(List<Integer> edge: connections){
            int node1 = edge.get(0);
            int node2 = edge.get(1);
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        //call dfs 
        dfs(0, 0, res, graph);
        return res;
    }
    private void dfs(int v, int u, List<List<Integer>> res, List<List<Integer>> graph){
        //base
        if(discovery[v] != -1) return; //if v is already discovered
        
        //logic
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for(int n : graph.get(v)){
            if(n == u)  continue;
            dfs(n, v, res, graph);
            if(lowest[n] > discovery[v]){
                res.add(Arrays.asList(n,v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }
	 
    
	// Driver code to test above
	public static void main (String[] args) {	
		CriticalConnections ob  = new CriticalConnections();	
		int n= 5;
		List<List<Integer>> connections = new ArrayList<>();
		connections.add(Arrays.asList(0,1));
		connections.add(Arrays.asList(1,2));
		connections.add(Arrays.asList(0,3));		
		connections.add(Arrays.asList(1,3));
		connections.add(Arrays.asList(3,4));
		
		System.out.println("Critical connections are : "+ob.criticalConnections(n, connections));
	}
}
