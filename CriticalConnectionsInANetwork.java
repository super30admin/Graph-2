import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Time Complexity: O(V + E)
 * Space Complexity: O(N)
 * Did this code successfully run on Leetcode: Yes
 * Any problem you faced while coding this: None

 * Approach:
 * The question involves determining the bridges. 
 * If the adjacent node cannot be reached by a node based on lowest insertion time,
 * that edge is considered a bridge
 */

public class CriticalConnectionsInANetwork {
    int timer;
    List<List<Integer>> adjList, result;
    int[] visited, timeOfInsertion, lowestTimeOfInsertion;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // create the graph
        for(List<Integer> connection: connections) {
            int u = connection.get(0);
            int v = connection.get(1);
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // instantiate visited array
        visited = new int[n];
        timeOfInsertion = new int[n];
        lowestTimeOfInsertion = new int[n];

        timer = 0;
        result = new ArrayList<>();
        
        dfs(0, -1);

        return result;
    }

    private void dfs(int node, int parent) {
        visited[node] = 1;
        timeOfInsertion[node] = lowestTimeOfInsertion[node] = timer;
        timer++;
        for (int n: adjList.get(node)) {
            // do not revisit the parent if it is adjacent
            if (n == parent) {
                continue;
            }

            // if the node is not already visited, visit it
            if (visited[n] == 0) {
                dfs(n, node);
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node], lowestTimeOfInsertion[n]);

                if (lowestTimeOfInsertion[n] > timeOfInsertion[node]) {
                    result.add(Arrays.asList(n, node));
                }
            } else {
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node], lowestTimeOfInsertion[n]);
            }
        }
    }
    public static void main(String[] args) {
        // Your code here
    }
}