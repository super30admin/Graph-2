// Time Complexity :O(V+E) where V is no of vertices and E is no of edges
// Space Complexity :O(V+E)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :was difficult to understand
class Solution {

    private int time;
    private HashMap<Integer, List<Integer>> map;
    List<List<Integer>> result;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        int[] lowest = new int[n];
        int[] discovery = new int[n];
        Arrays.fill(discovery, -1);
        result = new ArrayList<>();
        map = new HashMap<>();
        // building graph as it is non directional we'll add in both nodes
        for (int i = 0; i < connections.size(); i++) {
            int node1 = connections.get(i).get(0);
            int node2 = connections.get(i).get(1);
            if (!map.containsKey(node1)) {
                map.put(node1, new ArrayList<>());
            }
            if (!map.containsKey(node2)) {
                map.put(node2, new ArrayList<>());
            }
            map.get(node1).add(node2);
            map.get(node2).add(node1);
        }
        dfs(0, 0, lowest, discovery);
        return result;
    }

    public void dfs(int node, int parent, int[] lowest, int[] discovery) {
        // if node is already visited return
        if (discovery[node] != -1)
            return;
        // if node is not visited before, mark lowest and discovery
        lowest[node] = time;
        discovery[node] = time;
        time++;
        // traverse through all children
        List<Integer> childList = map.get(node);
        for (int child : childList) {
            if (child == parent)
                continue;
            // recurse
            dfs(child, node, lowest, discovery);
            // if child's lowest value is more than discovery of node, add in result
            if (lowest[child] > discovery[node]) {
                result.add(Arrays.asList(child, node));
            } // update lowest value
            lowest[node] = Math.min(lowest[node], lowest[child]);
        }
    }
}