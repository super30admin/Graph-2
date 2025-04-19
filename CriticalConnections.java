//The approach here is to use forward going dfs where we start from a node where we mark the time in discovery and lowest arrays and we traverse through the graph only once
//Time Complexity: O(v+e) where v and e represents vertices and edges of the graph
//Space Complexity: O(v+e)
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {
    HashMap<Integer, List<Integer>> map;
    List<List<Integer>> result;
    int[] discovery;
    int[] lowest;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.discovery = new int[n];
        this.lowest = new int[n];
        Arrays.fill(discovery, -1);
        Arrays.fill(lowest, -1);
        this.result = new ArrayList<>();
        this.map = new HashMap<>();

        //make adjacency list
        for(int i = 0; i<n; i++){
            map.put(i, new ArrayList<>());
        }
        buildGraph(connections);
        dfs(0,0); //starting from node 0
        return result;
    }

    private void buildGraph(List<List<Integer>> connections){
        for(List<Integer> edge: connections){
            map.get(edge.get(0)).add(edge.get(1));
            map.get(edge.get(1)).add(edge.get(0));
        }
    }

    private void dfs(int v, int u){
        //base
        if(discovery[v] != -1) return;
        //logic
        discovery[v] = time; lowest[v] = time; 
        time++;
        for(int ne: map.get(v)){
            if(ne == u) continue;
            dfs(ne, v);
            if(lowest[ne] > discovery[v]){
                result.add(Arrays.asList(ne,v));
            }
            lowest[v] = Math.min(lowest[ne], lowest[v]);
        }
    }
}