// Time Complexity : O(V+E)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        result = new ArrayList<>();

        int[] discovery = new int [n];
        int[] lower =new int[n];

        Arrays.fill(discovery, -1);
        buildGraph(n, connections);
        dfs(0, 0, discovery, lower, 0);

        return result;
    }
    private void buildGraph(int n, List<List<Integer>> connections){

        for(int x =0; x< n; x++){
            graph.add(new ArrayList<>());
        }

        for(List<Integer> edge :  connections){
            int source = edge.get(0);
            int dest = edge.get(1);

            graph.get(source).add(dest);
            graph.get(dest).add(source);
        }
    }

    private void dfs(int curr_v, int parent, int[] discovery, int[] lower, int time){
        if(discovery[curr_v] != -1)
            return;

        discovery[curr_v] = time;

        lower[curr_v] = time;
        time += 1;

        List<Integer> neighbours = graph.get(curr_v);

        for(int n : neighbours){
            if(n == parent)
                continue;

            dfs(n, curr_v, discovery, lower, time);

            if(lower[n] > discovery[curr_v]){
                result.add(Arrays.asList(n, curr_v));
            }

            lower[curr_v] = Math.min(lower[curr_v], lower[n]);
        }
    }
}
