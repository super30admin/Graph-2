import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TC : O(V+E)
// SC : O(V+E)
public class Problem1 {
    List<List<Integer>> result;
    List<List<Integer>> graph;
    int time;
    int[] discovery;
    int[] lowest;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        if (n == 0) return new ArrayList<>();

        result = new ArrayList<>();
        graph = new ArrayList<>();

        discovery = new int[n];
        lowest = new int[n];

        Arrays.fill(discovery, -1);

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        buildConnections(connections);

        dfs(0, 0);
        return result;
    }

    private void buildConnections(List<List<Integer>> connections) {
        for (List<Integer> connection : connections) {
            int from = connection.get(0);
            int to = connection.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }

    private void dfs(int v, int u) {
        //base case
        if (discovery[v] != -1) return;
        //logic
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for (int n : graph.get(v)) {
            if (n == u) continue;
            dfs(n, v);
            if (lowest[n] > discovery[v]) {
                result.add(Arrays.asList(n, v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }

}
