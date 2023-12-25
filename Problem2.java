// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class Solution {
    int[] colors;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        this.colors = new int[n];
        Arrays.fill(colors,-1);
        int color = 0;
        for(int i = 0; i < n; i++){
            if(colors[i] == -1){
                dfs(graph,i,color);
                color++;
            }
        }
        int[] group = new int[color];
        int[] groupinit = new int[color];
        for(int i = 0; i < colors.length; i++){
            group[colors[i]]++;
        }
        for(int i = 0; i < initial.length; i++){
            groupinit[colors[initial[i]]]++;
        }
        int res = Integer.MAX_VALUE;
        for(int node : initial){
            if(groupinit[colors[node]] == 1){
                if(res == Integer.MAX_VALUE)
                    res = node;
                else if(group[colors[node]] > group[colors[res]])
                    res = node;
                else if((group[colors[node]] == group[colors[res]]) && node < res)
                    res = node;
            }
        }
        if(res == Integer.MAX_VALUE){
            for(int node : initial)
                res = Math.min(res,node);
        }
        return res;
    }
    private void dfs(int[][] graph,int idx,int color){
        if(colors[idx] != -1)
            return;
        colors[idx] = color;
        for(int i = 0; i < graph.length; i++){
            if(graph[idx][i] == 1)
                dfs(graph,i,color);
        }
    }
}