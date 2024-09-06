/*
    LC - 924. Minimize Malware Spread
    TC - O(n2)
    SC - O(n)
*/

class Solution {
    int[] colors;
    int color;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        if(graph == null || graph.length == 0) {
            return 0;
        }
        int n = graph.length;
        colors = new int[n];
        Arrays.fill(colors, -1);
        for(int i=0; i<n; i++) {
            if(colors[i] == -1) {
                dfs(graph, i);
                color++;
            }
        }
        int[] groups = new int[color];
        for(int i=0; i<n; i++) {
            int index = colors[i];
            groups[index]++;
        }
        int[] infected = new int[color];
        for(int i=0; i<initial.length; i++){
            int currColor = colors[initial[i]];
            infected[currColor]++;
        }
        int result = -1;
        for(int i=0; i<initial.length; i++) {
            int currColor = colors[initial[i]];
            if(infected[currColor] == 1) {
                if(result == -1) {
                    result = initial[i];
                } else if(groups[currColor] > groups[colors[result]]) {
                    result = initial[i];
                }
                else if(groups[currColor] == groups[colors[result]] && result > initial[i]) {
                    result = initial[i];
                }
            }
        }
        if(result == -1) {
            int min = Integer.MAX_VALUE;
            for(int i=0; i<initial.length; i++) {
                min = Math.min(min, initial[i]);
            }
            return min;
        }
        return result;
   }
   private void dfs(int[][] graph, int v) {
        if(colors[v] != -1) {
            return;
        }
        colors[v] = color;
        for(int i=0; i< graph.length; i++) {
            if(graph[v][i] == 1) {
                dfs(graph, i);
            }
        }
   }
}