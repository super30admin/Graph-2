class Solution {//Time of (V+E) 
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        List<List<Integer>> results = new ArrayList<>();
        
        for(int i = 0 ; i<n ; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(List<Integer> list:connections){
            graph[list.get(0)].add(list.get(1));
            graph[list.get(1)].add(list.get(0));
        }
        
        int timer = 0;
        boolean[] visited = new boolean[n];
        int[] timeStamp = new int[n]; // lowlink
        helper(graph,-1,0,timer,visited,results,timeStamp);
        return results;
    }
    public void helper(List<Integer>[] graph, int parent, int node , int timer, boolean[] visited,List<List<Integer>> results, int[] timeStamp){
        //Mark the node
        visited[node] = true;
        timeStamp[node] = timer++;
        int currentTimeStamp = timeStamp[node];
        
        //Parse the neightbours
        for(int neighbour:graph[node]){
            if(neighbour == parent)
                continue;
            if(!visited[neighbour]){
                helper(graph,node,neighbour,timer,visited,results,timeStamp);
            }
            timeStamp[node] = Math.min(timeStamp[node],timeStamp[neighbour]);
            if(currentTimeStamp<timeStamp[neighbour]){
                results.add(Arrays.asList(node,neighbour));
            }
        }
    }
}