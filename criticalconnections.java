//Time Complexity:O(V+E)
//Space COmplexity:O(V+E)
//Approach: Tarjan's Algorithm-Need to have two arrays that keeps track of the discovered time and low time of the adjacent vertices. Next is to build a graph from the given connections array and fill all the elements in the disc array to -1. If for a particular index, if the disc array is -1, then we visit that node, calculate its children and move along that direction, until we detect a backedge. The backedge would give an indication that there is another path from which the connection can still be maintained and hence we will update the low time to be equal to the low time of the two vertices. Whenever the low time of the veryex is greter than the low time of its edge, then this would indicate that there is no backedge and hence would be a critical connection. 
//This code was executed successfully and got accepted in leetcode.

class Solution {
    int time=0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        int[] disc=new int[n];
        int[] low=new int[n];
        List<Integer>[] graph=new ArrayList[n];
        List<List<Integer>> res=new ArrayList<>();
        
        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<>();
        }
        Arrays.fill(disc,-1);
        
        for(int i=0;i<connections.size();i++){
            int from=connections.get(i).get(0);
            int to=connections.get(i).get(1);
            
            graph[from].add(to);
            graph[to].add(from);
        }
        for(int i=0;i<n;i++){
            if(disc[i]==-1){
                dfs(i,disc,low,graph,res,i);
            }
        }
        return res;
    }
    public  void dfs(int u,int[] disc,int[] low, List<Integer>[] graph,List<List<Integer>> res, int par){
        disc[u]=low[u]=++time;
        for(int i=0;i<graph[u].size();i++){
            int v=graph[u].get(i);
            
            if(v==par){
                continue;
            }
            if(disc[v]==-1){
                dfs(v,disc,low,graph,res,u);
                low[u]=Math.min(low[u],low[v]);
                
                if(low[v]>disc[u]){
                    res.add(Arrays.asList(u,v));
                }
            }
            else{
                low[u]=Math.min(low[u],disc[v]);
            }
        }
    }
}