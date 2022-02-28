// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int[] discovery;
    int[] lowest;
    int t;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        //o(V+e)time and o(n^2) time
        graph = new ArrayList<>();
        result = new ArrayList<>();
        discovery = new int[n];
        Arrays.fill(discovery,-1);
        lowest = new int[n];
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        buildgraph(connections);
        dfs(0, 0);
        return result;



    }
    private void buildgraph(List<List<Integer>> connections){
        for(List<Integer> edge: connections){
            int n1 = edge.get(0);
            int n2 = edge.get(1);
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
    }

    private void dfs(int v, int u){

        if(discovery[v] != -1) return;
        discovery[v] = t;
        lowest[v] = t;
        t++;
        for(int n:  graph.get(v)){
            if(n == u) continue;
            dfs(n, v);
            if(lowest[n] > discovery[v]){
                result.add(Arrays.asList(n,v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }

    int[] colors;
    int n;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        //O(n^2) time and O(n) space
        n = graph.length;
        colors = new int[graph.length];
        Arrays.fill(colors, -1);
        int len = 0;
        for(int i = 0; i < n; i++){
            if(colors[i] == -1){
                dfs(graph, i, len);
                len++;
            }
        }

        int[] groups = new int[len];
        for(int node: colors){
            groups[node]++;
        }
        int[] initGroups = new int[len];
        for(int node: initial){
            initGroups[colors[node]]++;
        }
        int result = Integer.MAX_VALUE;
        for(int node : initial){
            int col = colors[node];
            //how many are already affected;
            int infected = initGroups[col];
            if(infected == 1){
                if(result == Integer.MAX_VALUE){
                    result = node;
                } else if(groups[colors[node]] > groups[colors[result]]){
                    result = node;
                } else if(groups[colors[node]] == groups[colors[result]] && node < result){
                    result = node;
                }
            }
        }
        if(result == Integer.MAX_VALUE){
            for(int node: initial){
                result = Math.min(node, result);
            }
        }
        return result;
    }

    private void dfs(int[][] graph, int i, int col){


        //base

        if(colors[i] != -1) return;

        //logic
        colors[i] = col;
        for(int j = 0; j < graph[0].length; j++){
            if(graph[i][j] == 1){
                dfs(graph, j, col);
            }
        }

    }
}