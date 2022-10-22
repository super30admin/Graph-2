import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Time Complexity : O(V+E) where V = number of vertices (nodes), E = number of edges (connections)
// Space Complexity : O(n) where n = number of nodes
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
//1192. Critical Connections in a Network (Hard) - https://leetcode.com/problems/critical-connections-in-a-network/
// Time Complexity : O(V+E) where V = number of vertices (nodes), E = number of edges (connections)
// Space Complexity : O(n) where n = number of nodes
class Solution {

	List<List<Integer>> graph;
	List<List<Integer>> result;
	int[] discovery;
	int[] lowest;
	int time;

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		graph = new ArrayList<>();
		result = new ArrayList<>();
		discovery = new int[n];
		lowest = new int[n];

		Arrays.fill(discovery, -1);

		buildGraph(n, connections);

		dfs(0, 0);

		return result;
	}

	private void buildGraph(int n, List<List<Integer>> connections) {
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (List<Integer> edge : connections) {
			int n1 = edge.get(0);
			int n2 = edge.get(1);

			graph.get(n1).add(n2);
			graph.get(n2).add(n1);
		}
	}

	private void dfs(int v, int u) {
		// base
		if (discovery[v] != -1)
			return;

		// logic
		discovery[v] = time;
		lowest[v] = time;
		time++;
		for (int n : graph.get(v)) {
			if (n == u)
				continue;

			dfs(n, v);

			if (lowest[n] > discovery[v]) {
				result.add(Arrays.asList(n, v));
			}

			lowest[v] = Math.min(lowest[v], lowest[n]);
		}
	}
}