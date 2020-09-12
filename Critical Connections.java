// Time Complexity : O(E+V) 
// Space Complexity : O(E+V) 

class Solution {
    List<List<Integer>> ans;
    List<List<Integer>> graph;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ans = new ArrayList<>();
        graph = new ArrayList<>();
        int[] discovery = new int[n];
        int[] lowestTime = new int[n];
        Arrays.fill(discovery, -1);
        buildGraph(n, connections);
        dfs(0, 0, discovery, lowestTime);
        return ans;
    }
    private void buildGraph(int n, List<List<Integer>> connections) {
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : connections) {
            int from = edge.get(0);
            int to = edge.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }
    private void dfs(int currNode, int parent, int[] discovery, int[] lowestTime) {
        // base case
        if (discovery[currNode] != -1) return; 
        // logic
        discovery[currNode] = time;
        lowestTime[currNode] = time;
        time++;
        List<Integer> neighbors = graph.get(currNode);
        for (int v : neighbors) {
            if (v == parent) continue;
            dfs(v, currNode, discovery, lowestTime);
            if (lowestTime[v] > discovery[currNode]) {
                ans.add(Arrays.asList(currNode, v));
            }
            lowestTime[currNode] = Math.min(lowestTime[currNode], lowestTime[v]);
        }
    }
}