// Time Complexity : O(V + E)
// Space Complexity : O(V + E)
// Method used : DFS

class Solution {

    // This is Tarjan's algorithm

    // This is an adjacency list
    HashMap<Integer, List<Integer>> map;

    // This array is used to keep track of the nodes when they got discovered
    int[] discovery;

    // This array will give us information about at lowest how we could reach a node
    int[] lowest;

    // Initially the time stamp starts from 0
    int time = 0;

    List<List<Integer>> result;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        if(n == 0 || connections.size() == 0) return new ArrayList();

        map = new HashMap();
        discovery = new int[n];

        // Initially none of the nodes are discovered
        Arrays.fill(discovery, -1);

        lowest = new int[n];

        result = new ArrayList();

        //Traverse the nodes and just create empty array lists as values in the map
        for(int i = 0; i < n; i++) map.put(i, new ArrayList());

        // Let's build the graph as adjacency list
        buildGraph(connections);

        // Now let's perform a dfs. Here the first parameter is child node and 2nd parameter is parent
        dfs(0, 0);

        return result;
    }

    private void buildGraph(List<List<Integer>> connections)
    {
        // The connections list is given as connections = [[0,1],[1,2],[2,0],[1,3]]. Our goal is to make hashmap look as 
        // {0 : [1, 2], 1 : [0, 2, 3], 2 : [0, 1]}...

        for(List<Integer> connection : connections)
        {
            // Undirected graph
            map.get(connection.get(0)).add(connection.get(1));
            map.get(connection.get(1)).add(connection.get(0));
        }
    }

    private void dfs(int current_node, int parent)
    {
        // If the node is already visited skip it
        if(discovery[current_node] != -1) return;

        // Assign the discovery and lowest values of node to time and increment the time stamp
        discovery[current_node] = time;
        lowest[current_node] = time;

        ++time;

        // Perform a dfs on all it's children
        List<Integer> edges = map.get(current_node);

        // Here now the current_node becomes the parent for children nodes
        for(int children : edges)
        {
            // We came back again to parent node so skip it
            if(children == parent) continue;

            // Make a dfs call
            dfs(children, current_node);

            // This condition check will tell us that the connection is critical connection
            if(lowest[children] > discovery[current_node])
            {
                List<Integer> temp = new ArrayList();
                temp.add(children);
                temp.add(current_node);

                result.add(temp);
            }

            // Minimize the lowest value for the parent node
            lowest[current_node] = Math.min(lowest[current_node], lowest[children]);
        }
    }
}
