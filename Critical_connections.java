
//TC - O(V + E)
//SC - O(V + E)
import java.util.*;

class Solution1 {
	List<List<Integer>> result;
	List<List<Integer>> graph;
	int m;
	int[] discovery;
	int[] lower;
	int time;

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		m = n;
		result = new ArrayList<>();
		graph = new ArrayList<>();
		discovery = new int[n];
		lower = new int[n];
		Arrays.fill(discovery, -1);
		buildGraph(connections);
		dfs(0, 0);
		return result;
	}

	public void buildGraph(List<List<Integer>> connections) {
		for (int i = 0; i < m; i++) {
			graph.add(new ArrayList<>());
		}
		for (List<Integer> edge : connections) {
			int node1 = edge.get(0);
			int node2 = edge.get(1);
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
	}

	private void dfs(int v, int u) {
		// base
		if (discovery[v] != -1)
			return;
		// logic
		discovery[v] = time;
		lower[v] = time;
		time++;
		for (int n : graph.get(v)) {
			if (n == u)
				continue;
			dfs(n, v);
			if (lower[n] > discovery[v]) {
				result.add(Arrays.asList(n, v));
			}
			lower[v] = Math.min(lower[v], lower[n]);
		}
	}
}