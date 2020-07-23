//time o(V + E)
//space o(V+E)
class Solution {
    List<List<Integer>> result;
    List<List<Integer>> graph;
    int nodes;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        graph = new ArrayList<>();
        this.nodes = n;
        int[] discovery = new int[n];
        int[] lower = new int[n];
        
        Arrays.fill(discovery, -1);
        buildGraph(connections);
        dfs(0, 0, discovery, lower);
        return result;
    }
    
    private void buildGraph(List<List<Integer>> connections) {
        for(int i=0; i<nodes; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(List<Integer> connection: connections) {
            int node1 = connection.get(0);
            int node2 = connection.get(1);
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
    }
    
    private void dfs(int v, int u, int[] discovery, int[] lower) {
        //base case
        if(discovery[v] != -1)
            return;
        //logic
        discovery[v] = time;
        lower[v] = time;
        time++;
        List<Integer> neighbors = graph.get(v);
        for(int n: neighbors) {
            //n is neighbor, u is parent node
            if(n == u)
                continue;
            dfs(n, v, discovery, lower);
            if(lower[n] > discovery[v]) {
                result.add(Arrays.asList(n, v));
            }
            lower[v] = Math.min(lower[v], lower[n]);
        }
    }
}