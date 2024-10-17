// Problem 1192. Critical Connections in a Network
// Time Complexity : O(v+e)
// Space Complexity : O(v+e)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class Solution {
    List<List<Integer>> result;
    int[] discovery;
    int[] lowest;
    int time;
    HashMap<Integer, List<Integer>> map;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.result = new ArrayList<>();
        this.map = new HashMap<>();
        this.discovery = new int[n];
        Arrays.fill(discovery, -1);
        this.lowest = new int[n];
        this.time = 0;
        buildgraph(connections);
        dfs(0, -1);
        return result;
    }
    private void buildgraph(List<List<Integer>> connections) {
        for (List<Integer> edge : connections) {
            int n1 = edge.get(0);
            int n2 = edge.get(1);
            map.putIfAbsent(n1, new ArrayList<>());
            map.get(n1).add(n2);
            map.putIfAbsent(n2, new ArrayList<>());
            map.get(n2).add(n1);
        }
    }
   private void dfs(int v, int u) {
        discovery[v] = lowest[v] = time++;
        for (int n : map.get(v)) {
            if (n == u) continue;
            if (discovery[n] == -1) {
                dfs(n, v);
                lowest[v] = Math.min(lowest[v], lowest[n]);
                if (lowest[n] > discovery[v]) {
                    result.add(Arrays.asList(v, n));
                }
            } else {
                lowest[v] = Math.min(lowest[v], discovery[n]);
            }
        }
   }
}