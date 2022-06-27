class Solution {
    int n;
    int[] colors;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        if(graph == null || graph.length == 0) return 0;
        
        n = graph.length;
        colors = new int[n];
        Arrays.fill(colors, -1);
        int c = 0;
        
        for(int i = 0; i < n; i++){
            if(colors[i] == -1){
                dfs(graph, i, c);
                c++;
            }
        }
        int[] groups = new int[c];
        for(int i = 0; i < n; i++){
            int color = colors[i];
            groups[color]++;
        }
        
        int[] infected = new int[c];
        for(int i = 0; i < initial.length; i++){
            int color = colors[initial[i]];
            infected[color]++;
        }
        
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < initial.length; i++){
            int color = colors[initial[i]];
            if(infected[color] == 1){
                if(ans == Integer.MAX_VALUE){
                    ans = initial[i];
                }
                else if(groups[colors[ans]] < groups[color]){
                    ans = initial[i];
                }
                else if(groups[colors[ans]] == groups[color] && initial[i] < ans){
                    ans = initial[i];
                }
            }
        }
        
        if(ans == Integer.MAX_VALUE){
            for(int i = 0; i < initial.length; i++){
                ans = Math.min(ans, initial[i]);
            }
        }
        return ans;
    }
    
    private void dfs(int[][] graph, int i, int c){
        if(colors[i] != -1) return;
        
        colors[i] = c;
        for(int j = 0; j < n; j++){
            if(graph[i][j] == 1){
                dfs(graph, j, c);
            }
        }
    }
}

//time complexity O(n^2)
//space complexity O(n)