public class Problem1 {
    List<List<Integer>> res;
    List<List<Integer>> graph;
    int[] discovery;
    int[] lowest;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        if (connections==null) return Collections.emptyList();
        res = new ArrayList<>();
        graph = new ArrayList<>();
        discovery = new int[n];
        lowest = new int[n];
        time = 1;
        Arrays.fill(discovery, -1);
        buildGraph(connections, n);
        dfs(0,0);
        return res;
    }

    private void buildGraph(List<List<Integer>> connections, int n) {
        for (int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
        for (List<Integer> connection:connections) {
            graph.get(connection.get(0)).add(connection.get(1));
            graph.get(connection.get(1)).add(connection.get(0));
        }
    }

    private void dfs(int node, int parent) {
        if (discovery[node]!=-1) return;
        discovery[node] = time;
        lowest[node] = time++;
        for (int neighbor:graph.get(node)) {
            if (neighbor==parent) continue;
            dfs(neighbor, node);
            if (lowest[neighbor]>discovery[parent]) res.add(Arrays.asList(neighbor,parent));
            lowest[parent] = Math.min(lowest[neighbor], lowest[parent]);
        }
    }
}