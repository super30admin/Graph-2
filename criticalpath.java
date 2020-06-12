// Time Complexity :O(n)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        result = new ArrayList<>();
        time = 0;
        for(int i = 0 ; i<n; i ++)
        {
            graph.add(new ArrayList<>());
        }
        for(List<Integer> con: connections)
        {
            int from = con.get(0);
            int to = con.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        int [] discovery =  new int[n];
        Arrays.fill(discovery,-1);
        int [] lowest = new int[n];
        dfs(0,0,discovery,lowest);
        return result;
        
        
    }
    private void dfs(int u, int v, int[] discovery, int[] lowest)
    {
        //base case
        if(discovery[u]!=-1) return;
        //logic
        discovery[u] = time; lowest[u] = time++;
        for(int neighbour:graph.get(u))
        {
            if(neighbour!=v)
            {
                dfs(neighbour,u,discovery,lowest);
                if(lowest[neighbour]>discovery[u])
                {
                    result.add(new ArrayList<>(Arrays.asList(neighbour,u)));
                }
                lowest[u] = Math.min(lowest[neighbour],lowest[u]);
            }
        }
    }
    
}