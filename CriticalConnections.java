// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : Yes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CriticalConnections {

    HashMap<Integer, List<Integer>> map;
    List<List<Integer>> result;
    int[] discoveries;
    int[] lowest;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        map = new HashMap<>();
        discoveries = new int[n];
        lowest = new int[n];
        Arrays.fill(discoveries,-1);
        //build graph
        for(int i=0; i<n; i++){
            map.put(i,new ArrayList<>());
        }
        for(List<Integer> edge : connections){
            int first = edge.get(0);
            int second = edge.get(1);
            map.get(first).add(second);
            map.get(second).add(first);
        }
        time = 0;
        // identify lowest
        dfs(0, 0);
        return result;
    }

    private void dfs(int curr, int parent){

        if(discoveries[curr] != -1) return;
        discoveries[curr] = time;
        lowest[curr] = time;
        time++;
        for(int n: map.get(curr)){
            if(n == parent) continue;
            dfs(n,curr);

            if(lowest[n] > discoveries[curr]){
                result.add(Arrays.asList(n,curr));
            }

            lowest[curr] = Math.min(lowest[curr],lowest[n]);
        }
    }

}
