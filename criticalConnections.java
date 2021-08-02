// Time Complexity : (O(V+E))
// Space Complexity : O(E)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        result = new ArrayList<>();
        buildGraph(connections,n);
        int[] lowest = new int[n];
        int[] discovery = new int[n];
        Arrays.fill(discovery,-1);
        dfs(0,0,lowest,discovery);
        return result;
    }
    private void buildGraph(List<List<Integer>> connections,int n){
        for(int i = 0 ; i < n ; i++){
            graph.add(new ArrayList<>());
        }
        for(List<Integer> connection : connections){
            int u = connection.get(0);
            int v = connection.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
    }
    private void dfs(int v,int u, int[] lowest,int[] discovery){
        // Base
        if(discovery[v] != -1) return;
        //logic
        lowest[v] = time;
        discovery[v] = time;
        time++;
        List<Integer> neighbours = graph.get(v);
        for(int neighbour : neighbours){
            if(neighbour == u) continue;
            dfs(neighbour,v,lowest,discovery);
            if(lowest[neighbour] > discovery[v]){
                result.add(Arrays.asList(neighbour,v));
            }
            lowest[v] = Math.min(lowest[v],lowest[neighbour]);
             
        }
    }
}