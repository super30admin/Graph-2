// Time complexity is O(V+E)
// Space complexity is O(N)
// This solution is submitted on leetcode

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigN156CriticalConnectionDFSSolution {

	class Solution {
		List<List<Integer>> graph;
		List<List<Integer>> result;
		int time;

		public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
			graph = new ArrayList<>();
			result = new ArrayList<>();
			int[] discovery = new int[n];
			Arrays.fill(discovery, -1);
			int[] lowestTime = new int[n];
			builtGraph(n, connections);
			dfs(0, 0, discovery, lowestTime);
			return result;
		}

		private void builtGraph(int n, List<List<Integer>> connections) {
			for (int i = 0; i < n; i++) {
				graph.add(new ArrayList<>());
			}
			for (List<Integer> edges : connections) {
				int from = edges.get(0);
				int to = edges.get(1);
				graph.get(from).add(to);
				graph.get(to).add(from);
			}
		}

		private void dfs(int curr, int prev, int[] discovery, int[] lowestTime) {
			discovery[curr] = time;
			lowestTime[curr] = time;
			time++;
			List<Integer> edges = graph.get(curr);
			for (int neighbor : edges) {
				// case 1:
				if (neighbor == prev)
					continue;
				if (discovery[neighbor] == -1) {
					dfs(neighbor, curr, discovery, lowestTime);
					if (lowestTime[neighbor] > discovery[curr])
						result.add(Arrays.asList(neighbor, curr));
				}
				lowestTime[curr] = Math.min(lowestTime[curr], lowestTime[neighbor]);
			}
		}
	}
}