// Time Complexity: O(V + E)
// Space Complexity: O(E)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
import java.util.*;
class Solution {
    List<List<Integer>> graph; // [[], [], []]
    List<List<Integer>> result; // [[2, 4], [0, 3]]
    int[] discovery;
    int[] lowest;
    int time;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.graph = new ArrayList<>();
        this.result = new ArrayList<>();
        this.discovery = new int[n];
        Arrays.fill(discovery, -1);
        this.lowest = new int[n];
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        buildgraph(connections);
        dfs(0, 0);
        return result;
    }
    
    public void buildgraph(List<List<Integer>> connections) {
        for(List<Integer> edge: connections) {
            int source = edge.get(0);
            int destination = edge.get(1);
            graph.get(source).add(destination);
            graph.get(destination).add(source);
        }
    }
    
    public void dfs(int v, int u) {
        // base
        if(discovery[v] != -1) {
            return;
        }
        // logic
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for(int n: graph.get(v)) {
            if(n == u) continue;
            dfs(n, v);
            if(lowest[n] > discovery[v]) {
                result.add(new ArrayList<>(Arrays.asList(n, v)));
            }
            lowest[v] = Math.min(lowest[n], lowest[v]);
        }
    }
}