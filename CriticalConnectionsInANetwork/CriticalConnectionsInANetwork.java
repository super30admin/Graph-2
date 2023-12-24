/* Time Complexity : O(V+E) */
/* Space Complexity : O(V+E) */
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this :

class Solution {
    HashMap <Integer, List<Integer>> map;
    int[] discovery;
    int[] lowest;
    int time;
    List<List<Integer>> result;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.map = new HashMap<>();
        this.discovery = new int[n];
        this.lowest = new int[n];
        this.result = new ArrayList<>();
        Arrays.fill(discovery, -1);
        buildGraph(connections, n);
        dfs(0, 0);
        return result;        
    }

    private void buildGraph(List<List<Integer>> connections, int n){
        for(int i=0; i < n ; i++){
            map.put(i, new ArrayList<>());
        }
        for(List<Integer> edge: connections){
            int n1 = edge.get(0);
            int n2 = edge.get(1);
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }
    }

    private void dfs(int v, int u){//parent , child
        if(discovery[v] != -1) return;
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for(int node: map.get(v)){
            if(node == u) continue;
            dfs(node, v);
            if(lowest[node] > discovery[v])
                result.add(Arrays.asList(node, v));
            lowest[v] = Math.min(lowest[node], lowest[v]);
        }
    }
}
