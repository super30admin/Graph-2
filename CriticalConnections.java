// Time Complexity : O(V+E)
// Space Complexity : O(E)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
public class CriticalConnections {

    class Solution {
        private List<List<Integer>> graph;
        private List<List<Integer>> result;
        int[] discovery;
        int[] lowestRank;
        private int time;

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            graph = new ArrayList<>();
            result = new ArrayList<>();
            discovery = new int[n];
            Arrays.fill(discovery, -1); //O(V)
            buildGraph(connections, n);
            lowestRank = new int[n];
            dfs(0 , 0);
            return result;
        }

        private void buildGraph(List<List<Integer>> connections, int n) {
            for(int i=0; i<n; i++){
                graph.add(new ArrayList<>());
            }

            for(List<Integer> edge : connections) {
                int u = edge.get(0); //parent
                int v = edge.get(1); //child
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
        }

        private void dfs(int u, int v) { // u = parent, v = child
            //base if it is already visited
            if(discovery[v] != -1) return;

            //logic
            discovery[v] = time;
            lowestRank[v] = time;
            time++;
            for(int neighbor : graph.get(v)) {
                //if neighbor is again parent
                if(neighbor == u) continue;
                //otherwise
                dfs(v, neighbor);
                if(lowestRank[neighbor] > discovery[v]) {
                    result.add(Arrays.asList(neighbor, v));
                }
                lowestRank[v] = Math.min(lowestRank[v], lowestRank[neighbor]);
            }
        }
    }
}