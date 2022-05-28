//TC : O(n^2)
                // n = total no. of nodes in network
//SC : O(n) 

class Solution {
    int[] colors;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        
        colors = new int[n];
        
        Arrays.fill(colors, -1);
        
        int color = 0;
        for(int i = 0; i< n; i++){
            //if(colors[i] != -1){
                dfs(graph, i, color);
                color++;
            
        }
        
        int[] nodes = new int[color]; // Nodes in each color group
        for(int i : colors){
            System.out.println(i);
            nodes[i]++;
        }
        
        
        int[] init = new int[color];//Infected nodes in each color group
        for(int i : initial){
            int cl = colors[i]; // color of node
            init[cl]++;
        }
        
        //Calculating result
        int result = - 1;
        for(int node : initial){
            int cl = colors[node];
            
            int total = nodes[cl]; // total nodes
            int infected = init[cl];
            
            if(infected == 1){
                if(result == -1){
                result = node;
            }
            else if(nodes[cl] > nodes[colors[result]]){
                result = node;
            }else if(nodes[cl] == nodes[colors[result]] && node < result){
                result = node;
            }
            }
        }
        
        if(result == -1){
            int min = initial[0];
            for(int i : initial){
                min = Math.min(min, i);
            }
            result = min;
        }
        
        
        return result;
    }
    public void dfs(int[][] graph, int i, int color){
        if(colors[i] != -1)  return;
        
        colors[i] = color;
        for(int j = 0; j< graph.length; j++){
            if(graph[i][j] == 1)
                dfs(graph, j, color);
        }
    }
}