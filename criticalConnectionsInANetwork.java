/*
Problem: https://leetcode.com/problems/critical-connections-in-a-network/
TC: O(v + E)
SC: O(V + E)
*/


// Tarjan's algorithm
// Technically, any cycle detection algorithm (such as union find) should work.
class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        if (n == 0 || connections == null || connections.size() == 0)
            return new ArrayList<>();
        
        List<List<Integer>> criticalConnections = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        // Used to store the time at which the node was discovered during the traversal.
        // Also, used as a seen / visited array
        int discoveryTime[] = new int[n];
        // Used to store the time at which the node was discovered during the traversal.
        // For all nodes part of a cycle, this value is updated to the lowest value of the nodes in the cycle.
        int lowestTime[] = new int[n];
        
        Arrays.fill(discoveryTime, -1);
        
        buildGraph(graph, connections, n);
        traverse(graph, 1, 1, 0, discoveryTime, lowestTime, criticalConnections, n);
        
        return criticalConnections;
    }
    
    private void buildGraph(List<List<Integer>> graph, List<List<Integer>> connections, int n) {
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }
        
        for (List<Integer> c : connections) {
            int node1 = c.get(0);
            int node2 = c.get(1);
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
    }
    
    private void traverse(List<List<Integer>> graph, int node, int parent, int time, int discoveryTime[], int lowestTime[], List<List<Integer>> criticalConnections, int n) {
        if (discoveryTime[node] != -1) {
            // node already visited
            return;
        }
        
        discoveryTime[node] = time;
        lowestTime[node] = time;
        
        for (int neighbor : graph.get(node)) {
            // to avoid getting stuck in an infinite loop
            if (neighbor == parent)
                continue;
            
            traverse(graph, neighbor, node, time + 1, discoveryTime, lowestTime, criticalConnections, n);
            // neighbor and node are not part of a cycle, so this is a critical connection
            // If they were, their lowestTime values would become the same
            if (lowestTime[neighbor] > discoveryTime[node]) {
                criticalConnections.add(Arrays.asList(node, neighbor));
            }
            lowestTime[node] = Math.min(lowestTime[node], lowestTime[neighbor]);
        }
    }
}