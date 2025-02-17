// Tarzan's algorithm
// discover [], lowest []
// forwars looking dfz, child node reached the given destination faster than the parent
//O(V+E)
class Solution {
    int time;
    List<List<Integer>> result;
    int[] discover;
    int[] lowest;
    HashMap<Integer, List<Integer>> map;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.result = new ArrayList<>();
        this.map = new HashMap<>();
        this.discover = new int[n];
        this.lowest = new int[n];
        Arrays.fill(discover, -1);
        
        buildgraph(connections,map,n);
        dfs(0,0);
        return result;
    }

    private void buildgraph(List<List<Integer>> connections,HashMap<Integer, List<Integer>> map,int n){
        for(int i=0;i<n;i++){
            map.put(i, new ArrayList<>());
        }
        for (List<Integer> edge : connections) {     
                int n1 = edge.get(0);
                int n2 = edge.get(1);
                map.get(n1).add(n2); 
                map.get(n2).add(n1);  
        }

    }

    public void dfs(int v, int u) {
        if (discover[v] != -1)
            return;
        discover[v] = time;
        lowest[v] = time;

        for(int n:map.get(v)){
            if(n==u) continue;
            time++;
            dfs(n,v);
            if(lowest[n]>discover[v]){
                result.add(Arrays.asList(n,v));
            }

            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }
}