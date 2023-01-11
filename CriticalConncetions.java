import java.util.*;

/*
Time Complexity: O(V+E)
Space Complexity: O(E)
*/
class Graph {
    private int V;
    private LinkedList<Integer> adj[];
    int id = 0;
    public List<List<Integer>> bridges;

    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        bridges = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            adj[i] = new LinkedList();
        }
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    public void dfs(int at, boolean[] visited, int ids[], int low[], int parent) {
        visited[at] = true;
        id = id + 1;
        low[at] = id;
        ids[at] = id;

        Iterator<Integer> i = adj[at].iterator();
        while(i.hasNext()) {
            int to = i.next();
            if(to == parent)
                continue;
            if(!visited[to]) {
                dfs(to, visited, ids, low, at);
                low[at] = Math.min(low[to], low[at]);
                if(ids[at] < low[to]) {
                    List<Integer> intermedRes = Arrays.asList(at, to);
                    bridges.add(intermedRes);
                }
            } else {
                low[at] = Math.min(low[at], ids[to]);
            }
        }
    }
}

class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Graph g = new Graph(n);
        for(List<Integer> connection : connections) {
            g.addEdge(connection.get(0), connection.get(1));
        }

        int[] low = new int[n];
        int[] ids = new int[n];
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(visited[i] == false) {
                g.dfs(i, visited, ids, low, -1);
            }
        }

        return g.bridges;
    }
}