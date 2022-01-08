//minimize malware spread

//tc n square
//sc n
class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        
        int n = graph.length;
        int[] grouping = new int[n];
        
        for(int i = 0; i < n; i++){
            
            grouping[i] = -1;
        }
        
        int group = 0;
        
        for(int i = 0; i < n; i++){
            
            if(grouping[i] == -1){
                
                dfs(graph, grouping, i, group, n);
                group++;
            }
        }
        
        int[] groups = new int[group];
        
        for(int i = 0; i < n; i++){
            
            groups[grouping[i]]++;
        }
        
        int[] infected = new int[group];
        
        for(int i = 0; i < initial.length; i++){
            
            int node = initial[i];
            int grp = grouping[node];
            infected[grp]++;
        }
        
        int minIdx = n;
        int maxSavedNodes = 0;
        
        for(int i = 0; i < initial.length; i++){
            
            int node = initial[i];
            int grp = grouping[node];
            
            if(infected[grp] == 1){
                
                int currSavedNodes = groups[grp] - 1;
                
                //clashing case
                
                if(currSavedNodes == maxSavedNodes){
                    
                    minIdx = Math.min(minIdx, node);
                } 
                
                else if(currSavedNodes > maxSavedNodes){
                    
                    maxSavedNodes = currSavedNodes;
                    minIdx = node;
                }
            }
        }
        if(minIdx == n){
            for(int i = 0; i < initial.length; i++){
                minIdx = Math.min(minIdx, initial[i]);
            }
        }
        return minIdx;
    }
    
    private void dfs(int[][] graph, int[] grouping, int i, int group, int n){
        
        if(i == graph.length) return;
        
        grouping[i] = group;
        
        for(int j = 0; j < n; j++){
            
            if(graph[i][j] == 1 && grouping[j] == -1){
                
                dfs(graph, grouping, j, group, n);
                
            }
            
        }
        
    }
}
