class Solution {
    List<List<Integer>> result;
    HashMap<Integer,List<Integer>> map = new HashMap<>();
    int [] discovery;
    int [] lowest ;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.result= new ArrayList<>();
        this.discovery = new int[n];
        this.lowest = new int[n];
        Arrays.fill(discovery,-1);
        buildGraph(n,connections);
        dfs(0,0);
        return result;
    }
    private void buildGraph(int n,List<List<Integer>> connections ){

        for(int i=0;i<n;i++){
            map.put(i,new ArrayList<>());
        }
        for(List<Integer> edge:connections){
            int n1 = edge.get(0);
            int n2 = edge.get(1);
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }
    }
    private void dfs(int v,int u){
        if(discovery[v]!=-1) return;
        discovery[v]=time;
        lowest[v]=time;
        time++;
        for(int n:map.get(v)){
            if(n==u) continue;
            dfs(n,v);
            if(lowest[n]>discovery[v]){
                result.add(Arrays.asList(n,v));
            }
            lowest[v]=Math.min(lowest[v],lowest[n]);
        }
    }
}