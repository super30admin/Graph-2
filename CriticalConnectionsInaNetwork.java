// TC: O(V+E)
// SC: O(E)
// Did it run successfully on Leetcode? : Yes
class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList();
        graph = buildGraph(connections, n);
        System.out.println(graph);
        int[] discovery = new int[n];
        Arrays.fill(discovery, -1);
        int[] lowest = new int[n];
        // DFS
        dfs(discovery, lowest, 0, 0);
        return result;
    }
    private List<List<Integer>> buildGraph(List<List<Integer>> connections, int n)
    {
        graph = new ArrayList();
        for ( int i = 0; i < n; i++)
        {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : connections)
        {
            int from = edge.get(0);
            int to = edge.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        return graph;
    }
    private void dfs(int[] discovery, int[] lowest, int v, int u)  // v-> currentNode, u->parent
    {
        // base
        if (discovery[v] != -1)
            return;
        // logic
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for (int neighbor : graph.get(v))
        {
            if (neighbor == u)
                  continue;
            dfs(discovery, lowest, neighbor, v);    // neighbor-> currentNode, v->parent
            if (lowest[neighbor] > discovery[v])
                result.add(Arrays.asList(neighbor, v));
            if (lowest[v] > lowest[neighbor])
            {
                lowest[v] = lowest[neighbor];
            }                
        }
    }
}
