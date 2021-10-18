//TC:O(v+e)
//SC: O(v)
//running on leetcode: yes
class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int[] discovery;
    int[] lowest;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        if(connections==null || connections.size()==0) return new ArrayList<>();
        graph = new ArrayList<>();
        result = new ArrayList<>();
        discovery = new int[n];
        Arrays.fill(discovery, -1);
        lowest = new int[n];
        buildGraph(connections, n);
        dfs(0,0);
        return result;
    }
    private void buildGraph(List<List<Integer>> connections, int n){
        for(int i=0; i<n; i++){
            graph.add(new ArrayList<>());
        }
        for(List<Integer> edge : connections){
            int u = edge.get(0);
            int v = edge.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
    }
    private void dfs(int v, int u){
        if(discovery[v] != -1) return;
        discovery[v]=time;
        lowest[v]=time;
        time++;
        List<Integer> neighbors = graph.get(v);
        for(int n : neighbors){
            if(n==u) continue;
            dfs(n, v);
            if(lowest[n]>discovery[v]){
                result.add(Arrays.asList(n,v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }
}
