// Time Complexity : O(V + E)
// Space Complexity : O(V)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {
    int time;
    HashMap<Integer, List<Integer>> map;
    int[] discovery;
    int[] lowest;
    List<List<Integer>> result;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.map = new HashMap<>();
        this.discovery = new int[n];
        this.lowest = new int[n];
        this.result = new ArrayList<>();
        Arrays.fill(discovery, -1);
        Arrays.fill(lowest, -1);
        buildGraph(n, connections);
        dfs(0, 0);
        return result;
    }

    private void buildGraph(int n, List<List<Integer>> connections) {
        for (int i = 0; i < n; i++)
            map.put(i, new ArrayList<>());

        for (List<Integer> conn : connections) {
            int n1 = conn.get(0);
            int n2 = conn.get(1);
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }
    }

    private void dfs(int u, int v) {
        if (discovery[v] != -1)
            return;

        discovery[v] = time;
        lowest[v] = time;
        time++;

        for (int ne : map.get(v)) {
            if (ne == u)
                continue;
            dfs(v, ne);
            if (lowest[ne] > discovery[v])
                result.add(Arrays.asList(ne, v));
            lowest[v] = Math.min(lowest[v], lowest[ne]);
        }
    }
}