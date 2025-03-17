// Time Complexity : O(V+E)
// Space Complexity : O(V+E)

class Solution {
  List<List<Integer>> result;
  List<List<Integer>> graph;
  int[] discovery;
  int[] lowest;
  int time;
  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    if(connections == null || n == 0) return new ArrayList<>();

    result = new ArrayList<>();
    graph = new ArrayList<>();
    discovery = new int[n];
    lowest = new int[n];
    time = 0;
    Arrays.fill(discovery, -1);
    Arrays.fill(lowest, -1);

    for(int i = 0; i<n; i++) {
      graph.add(new ArrayList<>());
    }

    buildGraph(connections);
    dfs(0,-1);

    return result;
  }

  private void buildGraph(List<List<Integer>> connections) {
    for(List<Integer> connection: connections) {
      int from = connection.get(0);
      int to = connection.get(1);
      graph.get(from).add(to);
      graph.get(to).add(from);
    }
  }

  private void dfs(int node, int parent) {
    // Base
    if(discovery[node] != -1) return;
    // Logic
    discovery[node] = time;
    lowest[node] = time;
    time++;
    List<Integer> children = graph.get(node);
    for(int child: children) {
      if(child == parent) continue;
      dfs(child, node);
      if(lowest[child] > discovery[node]) result.add(Arrays.asList(child, node));
      lowest[node] = Math.min(lowest[node], lowest[child]);
    }

  }
}