//Time - O(V+E)
// Space - O(V+E)
class Solution {
    List<List<Integer>> result;
    List<List<Integer>> graph;
    int time;
    int[] discovery;
    int[] lowest;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        graph = new ArrayList<>();
        discovery = new int[n];
        lowest = new int[n];
        buildGraph(connections,n);
        Arrays.fill(discovery, -1);
        dfs(0,0);
        return result;
    }

    private void dfs(int v, int u){
        if(discovery[v]!=-1) return;
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for(int n : graph.get(v)){
            if(n==u) continue;
            dfs(n,v);
            if(lowest[n] > discovery[v]){
                result.add(Arrays.asList(n,v));
            }
            lowest[v] = Math.min(lowest[v],lowest[n]);
        }
    }

    private void buildGraph(List<List<Integer>> connections, int n) {
        for(int i=0; i<n;i++){
            graph.add(new ArrayList<>());
        }
        for(List<Integer> node: connections){
            int node1 = node.get(0);
            int node2 = node.get(1);
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
    }
}