// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : No
// Any problem you faced while coding this : No


class Solution {
    List<List<Integer>> result;
    List<List<Integer>> graph;
    int [] discovery;
    int [] lowest;
    int time;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        graph = new ArrayList<>();
        discovery = new int[n];
        lowest = new int[n];
        Arrays.fill(discovery, -1);
        Arrays.fill(lowest, -1);
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }    
        buildGraph(connections);
        dfs(0, -1);
        return result;
    }

    private void buildGraph(List<List<Integer>> connections){
        for(List<Integer> connection: connections){
            int from = connection.get(0);
            int to = connection.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }

    private void dfs(int v, int u){
        //base
        if(discovery[v] != -1){
            return;
        }

        //logic
        discovery[v] = time;
        lowest[v] = time;
        time++;
        List<Integer> children = graph.get(v);
        for(int n : children){
            if(n == u){
                continue;
            }
            dfs(n, v);
            if(lowest[n] > discovery[v]){
                result.add(Arrays.asList(n,v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }
}