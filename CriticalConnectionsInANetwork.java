// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
// Use single pass DFS(Tarjan's algorithm)
// Maintain two arrays - one discovery array for the natural order of discovery of a node in DFS
// lowest array - earliest discovered node that we can reach
class Solution {
    List<List<Integer>> result;
    int[] disc, lowest;
    HashMap<Integer,List<Integer>> map; int t;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        disc = new int[n]; Arrays.fill(disc,-1); lowest = new int[n];
        map = new HashMap<>();
        buildConnections(n,connections);
        dfs(0,0);
        return result;
    }
    public void buildConnections(int n, List<List<Integer>> connections){
        for(int i=0;i<n;i++) map.put(i, new ArrayList<>());
        for(List<Integer> c:connections){
            int x = c.get(0);
            int y = c.get(1);
            map.get(x).add(y);
            map.get(y).add(x);
        }
    }
    void dfs(int v, int u){
        if(disc[v]!=-1) return;
        disc[v] = t;lowest[v]=t;t++;
        for(int n:map.get(v)){
            if(n==u) continue;
            dfs(n,v);
            if(disc[v]<lowest[n])
                result.add(new ArrayList<Integer>(
      Arrays.asList(v,n)));
            lowest[v] = Math.min(lowest[n],lowest[v]);
        }
    }
}