// Time Complexity : O(V+E)
// Space Complexity :  O(V+E)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Approach : use tarjan's algorithm to find edges that are part of cycle. The edges that are not part of cycle are
/// critical connections
class Solution {
    List<List<Integer>> result;
    List<List<Integer>> map;
    int discovery[];
    int lowest[];
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        map = new ArrayList<>();
        discovery = new int[n];
        lowest = new int[n];
        for(int i=0;i<n;i++){
            map.add(new ArrayList<>());
        }
        for(List<Integer> edge: connections){
            map.get(edge.get(0)).add(edge.get(1));
            map.get(edge.get(1)).add(edge.get(0));
        }
        Arrays.fill(discovery, -1);
        Arrays.fill(lowest, -1);
        dfs(1, 1);
        return result;
    }
    private void dfs(int v, int u){
        if(discovery[v]!=-1){
            return;
        }
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for(int ne: map.get(v)){
            if(ne == u) continue;
            dfs(ne, v);
            if(lowest[ne]>discovery[v]){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(ne);
                temp.add(v);
                result.add(temp);
            }
            lowest[v] = Math.min(lowest[ne], lowest[v]);
        }
    }
}