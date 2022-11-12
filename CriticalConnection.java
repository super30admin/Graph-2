class Solution {
    //tc-o(v+e)
    //sc-o(v+e)
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int[] discovery;
    int[] lowest;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        if(connections == null) return result;
        graph = new ArrayList<>();
        discovery = new int[n];
        lowest = new int[n];
        Arrays.fill(discovery,-1);
        buildGraph(connections,n);
        dfs(0,0);
        return result;
    }
    private void buildGraph(List<List<Integer>> connections,int n)
    {
      for(int i=0;i<n;i++)
      {
          graph.add(new ArrayList<>());
      }
      for(List<Integer> edge: connections)
      {
          graph.get(edge.get(0)).add(edge.get(1));
          graph.get(edge.get(1)).add(edge.get(0));
      }

    }

    private void dfs(int v,int u)
    {
     if(discovery[v] != -1) return;
     discovery[v] = time;
     lowest[v] = time;
     time++;

     for(int n: graph.get(v))
     {
         if(n==u) continue;
         dfs(n,v);
         if(lowest[n]>discovery[v])
         {
             result.add(Arrays.asList(n,v));
         }
         lowest[v] = Math.min(lowest[n],lowest[v]);
     }
    }
}