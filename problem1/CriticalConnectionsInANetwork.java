// Time Complexity : O(V + E), V -> Number of vertices, E -> Number of Edges
// Space Complexity : O(V + E)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnectionsInANetwork {
	List<List<Integer>> ans;
	List<List<Integer>> graph;
	int[] discovery;
	int[] lowest;
	int time;

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		ans = new ArrayList<>();
		if (connections == null || connections.size() == 0 || n == 0) {
			return ans;
		}

		graph = new ArrayList<>();

		// Build the graph
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		buildGraph(connections);

		// DFS and update discovery order value and lowest node time
		lowest = new int[n];
		discovery = new int[n];
		Arrays.fill(discovery, -1);

		time = 0;

		dfs(0, 0);

		return ans;
	}

	private void buildGraph(List<List<Integer>> connections) {
		for (List<Integer> connection : connections) {
			int node1 = connection.get(0);
			int node2 = connection.get(1);

			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
	}

	private void dfs(int v, int u) {
		// Base
		if (discovery[v] != -1) {
			return;
		}
		// Logic
		lowest[v] = time;
		discovery[v] = time;
		time++;

		for (int n : graph.get(v)) {
			if (u == n) {
				continue;
			}
			dfs(n, v);

			if (lowest[n] > discovery[v]) {
				ans.add(Arrays.asList(n, v));
			}
			lowest[v] = Math.min(lowest[n], lowest[v]);
		}
	}

	private void print(List<List<Integer>> connections) {
		for (List<Integer> connection : connections) {
			for (Integer node : connection) {
				System.out.print(node + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		CriticalConnectionsInANetwork obj = new CriticalConnectionsInANetwork();
		int n = 4;
		List<List<Integer>> connections = new ArrayList<>();
		connections.add(new ArrayList<>(Arrays.asList(0, 1)));
		connections.add(new ArrayList<>(Arrays.asList(1, 2)));
		connections.add(new ArrayList<>(Arrays.asList(2, 0)));
		connections.add(new ArrayList<>(Arrays.asList(1, 3)));

		List<List<Integer>> ans = obj.criticalConnections(n, connections);

		System.out.println("Critical Connections: ");
		obj.print(ans);
	}

}
