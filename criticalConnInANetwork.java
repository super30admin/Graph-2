/**
TC: O(V+ 2E) : V: no of nodes, E : no. of edges
SC: O(V+2E) + 3V
 */

class Solution {
    int timer = 1;
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        HashMap<Integer,List<Integer>> adj = new HashMap<>();

        for(int i =0; i<n; i++)
            adj.put(i, new ArrayList<>());

        for(List<Integer> conn : connections){
            int u = conn.get(0); int v = conn.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }    

        int[] vis = new int[n];
        int[] low = new int[n];
        int[] time = new int[n];

        dfs(0, -1, vis, time, low, adj);

        return result;
    }


    private void dfs(int node, int parent, int[] vis, int[] time, int[] low, HashMap<Integer,List<Integer>> adj){

        vis[node] = 1;
        time[node] = low[node] = timer;
        timer++;

        for(int child : adj.get(node)){
            if(child == parent) continue;

            //child not visted
            if(vis[child] == 0){
                dfs(child, node, vis, time, low, adj);
                low[node] = Math.min(low[node], low[child]);
                
                //find bridge
                if(low[child] > time[node])
                    result.add(Arrays.asList(node, child));
            }else{
                low[node] = Math.min(low[child], low[node]);
            }
        }
    }
}