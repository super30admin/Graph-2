import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Time Complexity : O(N) = V + E
//Space Complexity : O(V)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/**
 * Form the graph using a list of lists. Maintain a time variable to store
 * current time and visited array to check if visited. Also maintain a timestamp
 * array to store the timestamp. Apply dfs on the graph starting with 0th node
 * and previous node as -1. Mark the node visited and update timestamp with
 * time++. Now iterate over the neighbors and if any of them is already
 * previous, continue else if it is not visited already, apply dfs on that node
 * with previous as current node. Then update timestamp of current node with the
 * minimum of neighbors vs current node. Then check if current node timestamp is
 * less than neighbor node's timestamp, then that means that is a critical
 * connection since we cannot reach the neighbor without passing current node.
 * So add that pair to the result list. Finally return result list.
 *
 */
class Solution {
	int time = 0;
	List<List<Integer>> result;

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		result = new ArrayList<>();
		List<Integer>[] adj = new ArrayList[n];
		for (int i = 0; i < n; i++)
			adj[i] = new ArrayList<>();

		for (List<Integer> edge : connections) {
			int x = edge.get(0);
			int y = edge.get(1);
			adj[x].add(y);
			adj[y].add(x);

		}
		boolean[] visited = new boolean[n];
		int[] timestamp = new int[n];
		dfs(adj, visited, timestamp, 0, -1);
		return result;
	}

	public void dfs(List<Integer>[] adj, boolean[] visited, int[] timestamp, int vertex, int prev) {
		visited[vertex] = true;
		timestamp[vertex] = time++;
		int currentTime = timestamp[vertex];

		for (int v : adj[vertex]) {
			if (v == prev)
				continue;
			if (!visited[v])
				dfs(adj, visited, timestamp, v, vertex);
			timestamp[vertex] = Math.min(timestamp[vertex], timestamp[v]);
			if (currentTime < timestamp[v])
				result.add(Arrays.asList(vertex, v));
		}
	}
}