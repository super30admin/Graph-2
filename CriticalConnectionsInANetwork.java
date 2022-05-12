// Time Complexity : O(V + E)
// Space Complexity : O(E)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// We will use the tarjan's algorithm to find the critical connections
// We will store all the critical connections and return them as result
class Solution {
    private int id;
    int[] lowestIds;
    List<List<Integer>> adjList;
    List<List<Integer>> result;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        adjList = getAdjList(n, connections);
        id = 0;
        lowestIds = new int[n];
        result = new ArrayList<>();
        Arrays.fill(lowestIds, -1);
        tarjan(0, -1);
        return result;
    }
    private int tarjan(int node, int parent){
        if(lowestIds[node] == -1){//unprocessed
            int nodeId = id;
            lowestIds[node] = nodeId;
            id++;
            for(int child: adjList.get(node)){
                if(child != parent){
                    int lowestChildId = tarjan(child, node);
                    if(lowestChildId > nodeId){
                        result.add(Arrays.asList(node, child)); //critical connection
                    }
                    else{
                        lowestIds[node] = Math.min(lowestIds[node], lowestChildId);
                    }
                }
            }
        }
        return lowestIds[node];
    }
    private List<List<Integer>> getAdjList(int n, List<List<Integer>> connections){
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            List<Integer> neigbhors = new ArrayList();
            adjList.add(neigbhors);   
        }
        for(List<Integer> conn: connections){
            int source = conn.get(0);
            int dest = conn.get(1);
            adjList.get(source).add(dest);
            adjList.get(dest).add(source);
        }
        return adjList;
    }
}