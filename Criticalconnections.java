//Time Complexity: O(E+V) [E being no of edges and V being number of vertices]
//Space Complexity: O(E+V)
//We record the timestamp that we visit each node. 
//For each node, we check every neighbor except its parent and return the smallest timestamp among all its neighbors.
//If this timestamp is strictly less than the node's timestamp, we know that this node is somehow in a cycle. 
//Otherwise, this edge from the parent to this node is a critical connection
class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List <Integer>[] graph = new ArrayList[n];
        for(int i=0; i<n; i++)
            graph[i] = new ArrayList<>();
        for(List<Integer> conn : connections)
        {
            graph[conn.get(0)].add(conn.get(1));
            graph[conn.get(1)].add(conn.get(0));
        }
        
        int timer = 0;
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int[] ts = new int[n];
        criticalConnectionsUtil(graph, -1, 0, timer, visited, results, ts);
        return results;
    }
    
    public void criticalConnectionsUtil(List<Integer>[] graph, int parent, int node, int timer, boolean[] visited, List<List<Integer>> results, int[] ts)
    {
        visited[node] = true;
        ts[node] = timer++;
        
        int currentts = ts[node];
        for( int n:graph[node] )
        {
            if( n == parent ) 
                continue;
            if(!visited[n]) 
                criticalConnectionsUtil(graph, node, n , timer, visited, results,ts);
            ts[node] = Math.min(ts[node],ts[n]);
            if(currentts<ts[n])
                results.add(Arrays.asList(node,n));
        }
        
    }
}