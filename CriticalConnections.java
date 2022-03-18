// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : Yes

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnections {

    List<List<Integer>> graph; //adjacency list
    int[] discovery; // natural discovery order of DFS
    int[] lowest; //lowest node that we can reach from curr node
    int time;
    int m;
    List<List<Integer>> result;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();

        //null
        if(n == 0 || connections == null) return result;
        graph = new ArrayList<>();
        discovery = new int[n];
        lowest = new int[n];
        m = n;
        Arrays.fill(discovery,-1);
        buildGraph(connections);
        dfs(0,0);
        return result;
    }

    private void buildGraph(List<List<Integer>> connections){
        for(int i = 0; i < m; i++){
            graph.add(new ArrayList<>());
        }

        for(List<Integer> conn : connections){
            int n1 = conn.get(0);
            int n2 = conn.get(1);
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
    }

    private void dfs(int curr, int parent){
        //base
        if(discovery[curr] != -1) return;

        //logic
        discovery[curr] = time;
        lowest[curr] = time;
        time++;
        //neighbors of curr node
        for(int n : graph.get(curr)){
            if(n == parent) continue;
            dfs(n, curr);
            if(lowest[n] > discovery[curr]){
                //earliest node i can reach from curr node > discovery order of parent
                //critical node
                result.add(Arrays.asList(n, curr));
            }
            //adjust lowest value of parent - earlies node that parent can reach from its                 //location
            lowest[curr] = Math.min(lowest[curr], lowest[n]);
        }
    }
}
