// Time Complexity : O(V+E)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

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
        time = 0;
        Arrays.fill(discovery, -1);
        buildGraph(connections, n);
        dfs(0,0);
        return result;
    }

    private void buildGraph(List<List<Integer>> connections, int n){
        for(int i=0; i<n; i++){
            graph.add(new ArrayList<>());
        }

        for(List<Integer> c : connections){
            int n1 = c.get(0);
            int n2 = c.get(1);
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
    }

    private void dfs(int v, int u){
        if(discovery[v] != -1) return;
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for(int n: graph.get(v)){
            if(n==u) continue;
            dfs(n,v);
            if(lowest[n]>discovery[v]){
                result.add(Arrays.asList(n,v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }
}