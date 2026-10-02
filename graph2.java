//problem1
class Solution {
    int[] discovery;
    int[] lowest;
    HashMap<Integer, List<Integer>> map;
    int time;
    List<List<Integer>> result;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.discovery = new int[n];
        this.lowest = new int[n];
        this.map = new HashMap<>();
        this.time = 0;
        this.result = new ArrayList<>();

        Arrays.fill(discovery, -1);

        for(int i=0; i<n; i++){
            map.put(i, new ArrayList<>());
        }

        for(List<Integer> connection : connections){
            int u = connection.get(0);
            int v = connection.get(1);

            map.get(u).add(v);
            map.get(v).add(u);
        }

        helper(0, -1);
        return result;
    }

    private void helper(int u, int v){

        if(discovery[u] != -1) return;

        discovery[u] = time;
        lowest[u] = time;
        time++;

        for(int ne : map.get(u)){
            if(ne == v) continue;

            helper(ne, u);

            if(lowest[ne] > discovery[u]){
                result.add(Arrays.asList(ne, u));
            }

            lowest[u] = Math.min(lowest[ne], lowest[u]);
        }
    }
}
//problem2
import java.util.*;

class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {

        int n = graph.length;

        int[] groups = new int[n];
        Arrays.fill(groups, -1);

        int group = 0;

        for (int i = 0; i < n; i++) {
            dfs(graph, groups, group, i);
            group++;
        }

        int[] totalInGroup = new int[group];

        for (int grp : groups) {
            totalInGroup[grp]++;
        }

        int[] initGroup = new int[groups.length];

        for (int node : initial) {
            int grp = groups[node];
            initGroup[grp]++;
        }

        int result = Integer.MAX_VALUE;
        int max = -1;

        for (int node : initial) {

            int grp = groups[node];

            if (initGroup[grp] == 1) {

                if (totalInGroup[grp] > max) {
                    max = totalInGroup[grp];
                    result = node;
                }

                if (totalInGroup[grp] == max) {
                    result = Math.min(result, node);
                }
            }
        }

        if (result == Integer.MAX_VALUE) {
            for (int node : initial) {
                result = Math.min(result, node);
            }
        }

        return result;
    }

    private void dfs(int[][] graph, int[] groups, int group, int i) {

        // base
        if (groups[i] != -1) {
            return;
        }

        // logic
        groups[i] = group;

        for (int j = 0; j < graph.length; j++) {

            if (graph[i][j] == 1) {
                dfs(graph, groups, group, j);
            }
        }
    }
}
