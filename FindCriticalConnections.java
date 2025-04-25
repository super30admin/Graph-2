class Solution {
    List<List<Integer>> res;
    int[] discovery;
    int[] lowest;
    List<List<Integer>> graph;
    int time;
    //Time Complexity: O (v + e)
    //Space Complexity: O (v)
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
             if (n ==0 || connections.size() ==0)
        return new ArrayList<>(); 
        res = new ArrayList<>();
        graph = new ArrayList<>();

        time = 0;
        discovery = new int[n];
        lowest = new int[n];
        Arrays.fill (discovery, -1);
        Arrays.fill (lowest, -1);
        for (int i =0 ; i < n;i++){
            graph.add (i, new ArrayList<>());
        }

        //building a graph
        for (List<Integer> l : connections){
            int node1 = l.get(0);
            int node2 = l.get(1);
            graph.get (node1).add (node2);
            graph.get (node2).add (node1);

            //entry to the grapgh
        }
            dfs (0, -1);
                return res;
    }

    private void dfs ( int vertex, int parent){
        if (discovery[vertex] != -1)
        return;

        discovery[vertex] = time;
        lowest[vertex] = time;
        time++;
        for (int c : graph.get(vertex)){
            if (c == parent)
            continue; 
            dfs (c, vertex);
            if (lowest[c] > discovery[vertex]){
                List<Integer> list = new ArrayList<>();
                list.add (c);
                list.add (vertex);
                res.add (list);
            }
            lowest[vertex] = Math.min (lowest[vertex], lowest[c]);
        }
    }
}
