
// Time Complexity : O(V+E)
// Space Complexity : O(V)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int[] discovery;
    int[] lowest;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        if(connections == null || connections.size() == 0 || n == 0) {
            return result;
        }
        graph = new ArrayList<>();
        
        for(int i = 0 ; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        buildGraph(connections);
        
        lowest = new int[n];
        discovery = new int[n];
        Arrays.fill(discovery, -1);
        dfs(0,0);
        
        return result;
    }
    private void buildGraph(List<List<Integer>> connections) {
        for(List<Integer> connection : connections) {
            int a = connection.get(0);
            int b = connection.get(1);
            
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
    }
    
    private void dfs(int node, int parent) {
        if(discovery[node] != -1) {
            return;
        }
        lowest[node] = time;
        discovery[node] = time;
        time++;
        for(int neighbor : graph.get(node)) {
            if(parent == neighbor) {
                continue;
            }
            dfs(neighbor, node);
            
            if(lowest[neighbor] > discovery[node]) {
                result.add(Arrays.asList(neighbor,node));
            }
            lowest[node] = Math.min(lowest[node],lowest[neighbor]);
        }
    }
}

// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


class Solution {
    int[] colors;
    int n;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        if(initial == null || initial.length == 0) {
            return 0;
        }
        n = graph.length;
        colors = new int[n];
        int color = 0;
        Arrays.fill(colors,-1);
        
        for(int i = 0 ; i < n; i++) {
            dfs(graph, i, color);
            color++;
        }
        
        int[] groups = new int[color];
        for(int c : colors) {
            groups[c]++;
        }
        
        int[] infectedGroups = new int[color];
        for(int node : initial) {
            int c = colors[node];
            infectedGroups[c]++;
        }
        
        int result = Integer.MAX_VALUE;
        
        for(int node : initial) {
            int c = colors[node];
            int infectedCount = infectedGroups[c];
            
            if(infectedCount == 1) {
                int nodes = groups[c];
                if(result == Integer.MAX_VALUE) {
                    result = node;
                } else if(nodes > groups[colors[result]]) {
                    result = node;
                } else if(nodes == groups[colors[result]] && node < result) {
                    result = node;
                }
            }
        }
        if(result == Integer.MAX_VALUE) {
            for(int node : initial) {
                result = Math.min(node,result);
            }
        }
        return result;
    }
    private void dfs(int[][] graph, int i, int color) {
        
        if(colors[i] != -1) {
            return;
        }
        
        colors[i] = color;
        
        for(int j = 0 ; j < n; j++) {
            if(graph[i][j] == 1) {
                dfs(graph,j,color);
            }
        }
    }
}

