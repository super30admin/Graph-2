//Time Complexity: O(V + E)
//Space Complexity: O(V + E)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkCriticalConnections {
    List<List<Integer>> result;
    List<List<Integer>> graph;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        graph = new ArrayList<>();
        int[] discovery = new int[n];
        Arrays.fill(discovery, -1);
        int[] lowestTime = new int[n];
        buildGraph(n, connections);
        dfs(0, 0, discovery, lowestTime);
        return result;
    }
    
    private void buildGraph(int n, List<List<Integer>> connections){
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for(List<Integer> edge: connections){
            int from = edge.get(0); int to = edge.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }
    
    private void dfs(int u, int prev, int[] discovery, int[] lowestTime){
        discovery[u] = time;
        lowestTime[u] = time;
        time++;
        List<Integer> edges = graph.get(u);
        for(int v: edges){
            if(v == prev) continue;
            if(discovery[v] == -1){
                dfs(v, u, discovery, lowestTime);
                if(lowestTime[v] > discovery[u]){
                    result.add(Arrays.asList(u, v));
                }
            }
        lowestTime[u] = Math.min(lowestTime[v], lowestTime[u]);
        }
    }
}
