class Solution {
    private List<List<Integer>> r;  
    private HashMap<Integer, List<Integer>> hm; 
    private int[] d, low;
    private int time;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        r = new ArrayList<>();
        d = new int[n];
        low = new int[n];
        time = 0;

        hm = new HashMap<>();

        Arrays.fill(d, -1);


        for (List<Integer> l : connections) {
            hm.putIfAbsent(l.get(0), new ArrayList<>());
            hm.putIfAbsent(l.get(1), new ArrayList<>());

            hm.get(l.get(0)).add(l.get(1));
            hm.get(l.get(1)).add(l.get(0));
        }

        dfs(0, -1);

        return r;
    }


    private void dfs(int u, int parent) {
        d[u] = low[u] = time++;
        
        List<Integer> neighbors = hm.get(u);

        for (int v : neighbors) {
            if (v == parent) continue;  

            if (d[v] == -1) { 
                dfs(v, u);
                

                low[u] = Math.min(low[u], low[v]);

                if (low[v] > d[u]) {
                    r.add(Arrays.asList(u, v));
                }
            } else {

                low[u] = Math.min(low[u], d[v]);
            }
        }
    }
}
