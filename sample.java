// Time Complexity : O(n)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    List<List<Integer>> result;
    List<List<Integer>> graph;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        result = new ArrayList<>();
        int[] discover =new int[n];
        int[] low =new int[n];
        Arrays.fill(discover,-1);
        graph(n,connections);
        dfs(0,0,discover,low);    
        return result;
    }
    public void graph(int n, List<List<Integer>> connections){
        for(int i = 0;i<n;i++){
            graph.add(new ArrayList<>());
        }
        for(List<Integer> edge : connections){
            int a = edge.get(0);
            int b = edge.get(1);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
    }
    public void dfs(int x, int prev,int[] dsicover,int[] low){
        if(dsicover[x] != -1) return;
        dsicover[x] = time;
        low[x] = time;
        time ++;
        List<Integer> edge = graph.get(x);
        for(int ver : edge){
            if(ver == prev) continue;
            dfs(ver, x, dsicover, low);
            if(low[ver]>dsicover[x]){
                result.add(Arrays.asList(x,ver));
            }
            low[x] = Math.min(low[ver],low[x]);
        }



    }
}