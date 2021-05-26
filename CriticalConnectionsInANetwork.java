class CriticalConnectionsInANetwork {

    // Time Complexity: O(n)
    // Space Complexity: O(n)
    
    int time = 0;
    List<List<Integer>> result;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Creating Adjacency List of the graph
        
        // Taking array of lists
        List<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        
        // populating the adjacency list
        for(List<Integer> edge : connections){
            int a = edge.get(0);
            int b = edge.get(1);
            
            adj[a].add(b);
            adj[b].add(a);
        }
        
        boolean[] visited = new boolean[n];
        int[] timestamp = new int[n];
        result = new ArrayList<>();
        
        // DFS function params --> adj list, visited array, timestamp array, starting node, node which we came from
        dfs(adj, visited, timestamp, 0, -1);
        return result;
    }
    
    private void dfs(List<Integer>[] adj, boolean[] visited, int[] timestamp, int curr, int prev){
        // mark the node as visited
        visited[curr] = true;
        
        // store the current timestamp for this node
        timestamp[curr] = time++;
        
        // store the timestamp for the current node
        int currTimeStamp = timestamp[curr];
        
        // loop through the connected nodes from the current node
        for(int v : adj[curr]){
            // we don't visit the same node from which we came
            if(v == prev)
                continue;
            
            // if not visited --> then go ahead and explore it
            if(!visited[v])
                dfs(adj, visited, timestamp, v, curr);
         
            // if node "v" has been reached in a time lesser than the curr node's timestamp
            timestamp[curr] = Math.min(timestamp[curr], timestamp[v]);
            
            // if the neighbor node's timestamp is greater than the curr node's timestamp --> CRITICAL EDGE
            if(currTimeStamp < timestamp[v])
                result.add(Arrays.asList(curr, v));
        }
    }
}