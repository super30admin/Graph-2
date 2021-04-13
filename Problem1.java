// Time Complexity :O(V(V+E))
// Space Complexity :O(N)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
class Solution {
    private HashMap<Integer, List<Integer>> graph= new HashMap<>();
    private int time=0;
    private List<List<Integer>> ans = new ArrayList<>();
    private void BuildGraph(List<List<Integer>> connections){//making a hashmap graph through list of connections
         for(List<Integer> edges : connections){
            int v=edges.get(0);
            int u=edges.get(1);
            
            graph.putIfAbsent(v,new ArrayList<>());
            graph.putIfAbsent(u,new ArrayList<>());
            
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        BuildGraph(connections);
        int[] visited=new int[n];//visited nodes according to timestamp
        int[] lower= new int[n];//first time the node vas visited
        Arrays.fill(visited,-1);
        
        dfs(0,0,visited,lower);
        return ans;
    }
    private void dfs(int curr, int parent, int[] d, int[] l){
        d[curr]=time;
        l[curr]=time;
        time++;
        
        for(int neighbor: graph.get(curr)){
            if(neighbor == parent)
                continue;
            if(d[neighbor]==-1)
                dfs(neighbor, curr,d,l);
            
            if(l[neighbor]>d[curr]){
                ans.add(Arrays.asList(neighbor,curr));//if current node was visited ealier then it means there is an alternate path as well 
            }
            l[curr]=Math.min(l[neighbor],l[curr]);
        }
    }
}