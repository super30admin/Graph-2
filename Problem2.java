// Time Complexity :O(N^2)
// Space Complexity :O(N)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n= graph.length;
        int[] colors= new int[n];//assign same color to connected nodes
        Arrays.fill(colors,-1);
        int color=0;
        for(int i=0;i<graph.length;i++){
            if(colors[i]==-1){
                dfs(i,color,colors,graph);
            }
             color++;
        }
        int[] groups= new int[color];//finding number of node in each color group
        for(int c: colors){
            groups[c]++;
        }
        int[] affected= new int[color];//total intial affected nodes of same color.
        for(int j: initial){
            affected[colors[j]]++;
        }
        int result=Integer.MAX_VALUE;
        for(int j : initial){
            int c= colors[j];
            int g= groups[c];
            int a= affected[c];
            if(a==1){//we can not save the graph with more than 1 affected node of same color
                if(result==Integer.MAX_VALUE){
                    result=j;
                }
                else if(g>groups[colors[result]]){
                    result=j;//saving the group with max connected nodes
                }
                else if(g==groups[colors[result]] && result>j){
                    result=j;
                }
            }
            
        }
        if(result==Integer.MAX_VALUE){
            for(int j: initial){
                result=Math.min(result,j);
            }
        }
        return result;
    }
    public void dfs(int v, int color, int[] colors, int[][] graph){
        colors[v]=color;
        for(int node=0; node<graph.length; node++){
            int edge=graph[v][node];
            
            if(edge==1 && colors[node]==-1){
                dfs(node,color,colors,graph);
            }
        }
    }
}