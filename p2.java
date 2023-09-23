// Time Complexity :O(n*n)
// Space Complexity :O(n*n)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
    //colors array to find separate groups
    int[] colors;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        colors = new int[graph.length];
        //Make every color as -1 to mark as unvisited
        for(int i=0; i<graph.length; i++){
            colors[i] = -1;
        }
        //Find out group of each element
        int clr = 0;
        for(int i=0; i<graph.length; i++){
            if(colors[i] == -1){
                dfs(graph, i, clr);
                clr++;
            }
        }
        
        //Find out number of elements in each grp
        int[] clrcnt = new int[clr];
        for(int i=0; i<graph.length; i++){
            clrcnt[colors[i]]++;
        }
        //Find out number of initial elemtns in each grp
        int[] incnt = new int[clr];
        for(int i=0; i<initial.length; i++){
            incnt[colors[initial[i]]]++;
        }

        //Go through each initial
        int result = Integer.MAX_VALUE;
        for(int i=0; i<initial.length; i++){
            int col = colors[initial[i]];
            int cnt = incnt[col];
            //If number of initial in a grp is equal to 1 then proceed
            if(cnt == 1){
                int colcnt = clrcnt[col];
                //If we have not found the result yet
                if(result == Integer.MAX_VALUE){
                    result = initial[i];
                }
                //If  result can save less nodes
                else if(clrcnt[colors[result]] < colcnt){
                    result = initial[i];
                }
                //If result saves same number of nodes
                else if(clrcnt[colors[result]] == colcnt && result > initial[i]){
                    result = initial[i];
                }
            }
        }

        //If we have not found result still
        if(result == Integer.MAX_VALUE){
            for(int i=0; i<initial.length; i++){
                result = Math.min(result, initial[i]);
            }
        }

        return result;
    }

//Resursive function to get the colors
    private void dfs(int[][] graph, int i, int clr){
        if(colors[i] != -1){
            return;
        }
        colors[i] = clr;
        for(int j=0; j<graph.length; j++){
            if(graph[i][j] == 1){
                dfs(graph, j, clr);
            }
        }

        return;
    }
}