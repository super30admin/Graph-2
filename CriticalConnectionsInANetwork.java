class Solution {
    HashMap<Integer, List<Integer>> map;
    List<List<Integer>> result;
    int[] discovery;
    int[] lowest;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.result = new ArrayList<>();
        this.discovery = new int[n];
        this.lowest = new int[n];
        this.map = new HashMap<>();
        this.time = 0;
        Arrays.fill(discovery,-1);
        for(int i=0;i<n;i++){
            map.put(i,new ArrayList<>());
        }

        for(List<Integer> edge: connections){
            map.get(edge.get(0)).add(edge.get(1));
            map.get(edge.get(1)).add(edge.get(0));
        }
        dfs(0,-1);
        return result;
    }


    private void dfs(int v, int u){

        if(discovery[v]!=-1) return;
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for(int ne: map.get(v)){
            if(ne == u) continue;
            dfs(ne,v);

            if(lowest[ne]>discovery[v]){
                result.add(Arrays.asList(ne,v));
            }
            lowest[v] = Math.min(lowest[ne],lowest[v]);
        }

    }
}