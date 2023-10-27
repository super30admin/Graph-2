// Time Complexity : O(n2) // we are calling dfs (which takes n time) for every row(n rows)
// Space Complexity : O(n) for colors array
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    int colors[];
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        if(graph==null || n==0) return -1;
        colors = new int[n];
        //n
        Arrays.fill(colors,-1);   // to mark every node with its color based on adjacency matrix at its index and keep track of visited
        int cl = 0;
        //n
        for(int i=0; i<n;i++){
            if(colors[i]==-1){
                dfs(graph,i,cl); //n
            }
            cl++;
        }
        int[] groups = new int[cl];

        // iterating over colors and store no.of nodes are there at each color
        for(int node: colors){ //here node are marked with colors like 0,1,2...cl
            groups[node]++; // increasing group[cl]
        }

        int[] initGroup = new int[cl];

        //iterate over the intial array and store the count of infected nodes in each grp
        for(int node: initial){
            int color = colors[node];  // which color or grp
            initGroup[color]++;    // increase the infected count for that color group
        }

        //iterate over the intial array to find teh infected node which removal would preverse max nodes
        int result = Integer.MAX_VALUE;
        int min =Integer.MAX_VALUE;
        for(int node: initial){
            min = Math.min(min, node);   //keep track of min element from the infected nodes
            int color = colors[node];    // fetch the color group
            if(initGroup[color]==1){   // if same group has more than 1 infected node dont consider removing it
            if(result == Integer.MAX_VALUE){  // for the first time save it with node
                result = node;     //result has the node 
            }
            else if(groups[node]>groups[colors[result]]){   // compare the neighbor count of current node and previous node
                result = node; // update with curr node as it has more neighbours that can be saved
            }
            else if(groups[node]==groups[colors[result]] && node < result){ //if saved neighno. is same take min node
                result = node;
            }
            }
        }
        if(result == Integer.MAX_VALUE){     //when initGroup!=1 for evry color grp we can have max value in result
            return min; // then return min of infected nodes
        }
        return result;



    }
    //O(n)
    private void dfs(int[][] graph, int i, int cl){
        //base
        if(colors[i]!=-1) return;  // keeping track of visited or not
        //logic
        colors[i]=cl;
        for(int j=0;j<graph.length;j++){ // iterate over every column for each row i
            if(graph[i][j]==1) { // check adjacency and proceed with recursion
                dfs(graph,j,cl);
            }
        }
    }
}