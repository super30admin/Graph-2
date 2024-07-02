import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
// TC: O(V+E)
// SC: O(V+E)
public class Problem1 {
    int[] discovery;
    int[] lowest;
    List<List<Integer>> result;
    HashMap<Integer, List<Integer>> adjacencyList;
    int timestamp;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.result = new ArrayList<>();
        this.discovery = new int[n];
        this.lowest = new int[n];
        this.timestamp=0;
        this.adjacencyList=new HashMap<>();

        //create adjacency list
        for(int i=0;i<n;i++){
            adjacencyList.put(i, new ArrayList<>());
        }

        for(List<Integer> connection: connections){
            adjacencyList.get(connection.get(0)).add(connection.get(1));
            adjacencyList.get(connection.get(1)).add(connection.get(0));
        }

        Arrays.fill(discovery,-1);
        dfs(0,-1);
        return result;
    }

    private void dfs(int curr, int parent){
        //base condition
        if(discovery[curr]!=-1) return;

        //logic
        discovery[curr]=timestamp;
        lowest[curr] = timestamp;
        timestamp++;

        for(int neighbour: adjacencyList.get(curr)){
            if(neighbour==parent) continue;
            dfs(neighbour,curr);
            if(lowest[neighbour]>discovery[curr]){
                result.add(Arrays.asList(neighbour,curr));
            }
            lowest[curr]=Math.min(lowest[neighbour],lowest[curr]);
        }

    }
}
