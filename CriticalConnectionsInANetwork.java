// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We initialize discovery and lowest arrays for each node and also maintain a adjacency map for the edges
or connections of the nodes.If a neighbour's lowest is greater than current's discovery, its a critical edge.
We also update the current's lowest if its greater than neighbour's lowest.We do recursive dfs to compute
all such critical edges.
 */
class Solution {
    List<List<Integer>> result;
    int[] discovery;
    int[] lowest;
    int time;
    Map<Integer, List<Integer>> map;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.map = new HashMap<>();
        this.result = new ArrayList<>();
        this.discovery = new int[n];
        this.lowest = new int[n];
        Arrays.fill(discovery, -1);

        for(int i = 0 ; i < n ; i++) {
            map.put(i, new ArrayList<>());
        }

        for(List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);

            map.get(u).add(v);
            map.get(v).add(u);
        }

        dfs(1, -1);
        return result;
    }

    private void dfs(int u , int v) {
        if(discovery[u] != -1)
            return;

        discovery[u] = time;
        lowest[u] = time;
        time++;

        List<Integer> neighbours = map.get(u);
        if(neighbours != null) {
            for(int ne : neighbours) {
                if(ne == v)
                    continue;
                dfs(ne, u);
                if(lowest[ne] > discovery[u])
                    result.add(Arrays.asList(ne, u));
                lowest[u] = Math.min(lowest[u], lowest[ne]);
            }
        }
    }
}