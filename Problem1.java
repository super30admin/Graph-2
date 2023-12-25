class Solution {
    List<List<Integer>> res;
    HashMap<Integer,List<Integer>> map;
    int[] discovery;
    int[] lowest;
    int time = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.discovery = new int[n];
        Arrays.fill(discovery,-1);
        this.res = new ArrayList<>();
        this.map = new HashMap<>();
        this.lowest = new int[n];
        buildgraph(n,connections);
        dfs(0,0);
        return res;
    }
    private void buildgraph(int n,List<List<Integer>> connections){
        for(int i = 0; i < n; i++)
            map.put(i, new ArrayList<>());
        for(List<Integer> edge : connections){
            int u = edge.get(0);
            int v = edge.get(1);
            map.get(u).add(v);
            map.get(v).add(u);
        }
    }
    private void dfs(int u, int v){
        if(discovery[v]!= -1)
            return;
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for(int n : map.get(v)){
            if(n == u)
                continue;
            dfs(v , n);
            if(lowest[n] > discovery[v])
                res.add(Arrays.asList(v,n));
            lowest[v] = Math.min(lowest[v],lowest[n]);
        }
        
    }
}