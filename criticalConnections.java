// time complexity : O(N+E)
// space complexity : O(N+E)
// problem Link : https://leetcode.com/problems/critical-connections-in-a-network/description/


import java.util.*;
class Solution {
    int time = 0;
    List<List<Integer>> adjList = new ArrayList<>();
    List<List<Integer>> result ;
    int[] visited;
    int[] latestVisited;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        visited = new int[n];
        latestVisited = new int[n];
        buildGraph(n,connections);
        result = new ArrayList<>();
        Arrays.fill(visited, -1);
        dfs(0,-1);
        return result;
    }

    // implement tarjans algorithm
    private void dfs(int curr, int parent){
        // already explored; exit;
        if(visited[curr] != -1)
            return ;
        // else explore 
        visited[curr] = ++time;
        latestVisited[curr] = time;
        List<Integer> children = adjList.get(curr);
        for(int child : children){
            if(child == parent)
                continue;
            dfs(child, curr);
            if(latestVisited[child] > visited[curr])
                result.add(Arrays.asList(child, curr));
            latestVisited[curr] = 
                Math.min(latestVisited[curr], latestVisited[child]);
        }
        
    }

    // impement the adj list
        private void buildGraph(int n, List<List<Integer>> connections){
            for(int i = 0 ; i < n ; i++)
                adjList.add(new ArrayList<Integer>());
            for(List<Integer> connection : connections){
                adjList.get(connection.get(0)).add(connection.get(1));
                adjList.get(connection.get(1)).add(connection.get(0));
            }
        }

}
