public class Solution {
    // TC => O(V+E)
    // SC => O(V+E)
    List<IList<int>> result;
    List<List<int>> graph;
    int[] discovery;
    int[] lowest;
    int time;
    public IList<IList<int>> CriticalConnections(int n, IList<IList<int>> connections) {
        if(n == 0){
            return new List<IList<int>>();
        }
        result = new List<IList<int>>();
        graph = new List<List<int>>();
        discovery = new int[n];
        lowest = new int[n];
        Array.Fill(discovery, -1);
        Array.Fill(lowest, -1);

        for(int i = 0; i < n; i++){
            graph.Add(new List<int>());
        }

        BuildGraph(n, connections);
        Dfs(0,-1);
        return result;
    }

    public void BuildGraph(int n, IList<IList<int>> connections){
        foreach(var con in connections){
            int from  = con[0];
            int to = con[1];
            graph[from].Add(to);
            graph[to].Add(from);
        }
    }

    public void Dfs(int v, int u){
        if(discovery[v] != -1){
            return;
        }

        discovery[v] = time;
        lowest[v] = time;
        time++;

        List<int> children = graph[v];

        foreach(var n in children){
            if(n == u){
                continue;
            }
            Dfs(n, v);
            if(lowest[n] > discovery[v]){
                result.Add(new List<int>(){n,v});
            }
            lowest[v] = Math.Min(lowest[n], lowest[v]);
        }
    } 
}
