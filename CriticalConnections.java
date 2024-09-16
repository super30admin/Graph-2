Time Complexity = O(V+E)
Space Complexity = O(V+E)

class Solution {
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        List<List<Integer>> result = new ArrayList<>();
        
        // Graph
        Map<Integer, List<Integer>> map = new HashMap<>();
        boolean[] visited = new boolean[n];
        int[] lowTime = new int[n];
        int time = 0;

        for (List<Integer> connection : connections) {
            int u = connection.get(0); 
            int v = connection.get(1);
          
            map.putIfAbsent(u, new ArrayList<>());
            map.putIfAbsent(v, new ArrayList<>());
            
            map.get(u).add(v);
            map.get(v).add(u);
        }
        
   
        for (int i = 0; i < map.size(); i++) {
            if (!visited[i]) {
                dfs(i, i, visited, lowTime, map, time, result);
            }
        }
        
        return result;
    }
    
    private void dfs(int current, int parent, boolean[] visited, int[] lowLinkValues, Map<Integer, List<Integer>> map, int time, List<List<Integer>> criticalConnections) {
        // Increment time per cycle
        time++;
        
        lowLinkValues[current] = time;
        
        int discoveryTime = time;
        
        visited[current] = true;
        
        for (int vertice : map.get(current)) {
            if (vertice == parent) continue;

            if (!visited[vertice]) {
                
                dfs(vertice, current, visited, lowLinkValues, map, time, criticalConnections);
                
                if (discoveryTime < lowLinkValues[vertice]) {
                    criticalConnections.add(Arrays.asList(current, vertice));
                }
            }
            lowLinkValues[current] = Math.min(lowLinkValues[current], lowLinkValues[vertice]);
        }
    }
}