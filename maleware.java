// Time Complexity : O(v^2)
// Space Complexity : O(n)

// we're dividing all nodes into groups, with connected nodes and color coding them using dfs.

class Solution {
    int[] color;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        color = new int[n];
        Arrays.fill(color,-1);

        //color coding each node using dfs
        int c=0;
        for(int i=0; i<n; i++){
            if(color[i]==-1){
                dfs(graph, i, c);
                c++;
            }
        }

        //number of connected node for particular color code.
        int[] group = new int[c];
        for(int i=0; i<n; i++){
            group[color[i]]++;
        }

        //number of infected nodes present in one color code.
        int[] initgroup = new int[c];
        for(int node : initial){
            initgroup[color[node]]++;
        }

        int result = Integer.MAX_VALUE;
        for(int node : initial){
            int cl = color[node];
            if(initgroup[cl] == 1){
                if(result == Integer.MAX_VALUE){
                    result = node;
                } else if(group[cl]>group[color[result]]){
                    result = node;
                } else if(group[cl]==group[color[result]] && node<result){
                    result = node;
                }
            }
        }

        if(result == Integer.MAX_VALUE){
            for(int node : initial){
                result = Math.min(result, node);
            }
        }

        return result;

    }

    private void dfs(int[][] graph, int i, int c){
        //base
        if(color[i] != -1) return;
        color[i] = c;
        //logic
        for(int j=0; j<graph.length; j++){
            if(graph[i][j] == 1){
                dfs(graph, j, c);
            }
        }

    }
}