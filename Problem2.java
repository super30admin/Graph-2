class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0;i<n;i++)
            graph.add(new ArrayList<>());

        HashSet<List<Integer>> hs = new HashSet<>();
        for(List<Integer> current:connections){
            graph.get(current.get(0)).add(current.get(1));
            graph.get(current.get(1)).add(current.get(0));
            hs.add(Arrays.asList(Math.min(current.get(0),current.get(1)),Math.max(current.get(0),current.get(1))));
        }

        int rank[] = new int[n];
        Arrays.fill(rank, -2);
        dfs(graph, rank, 0, hs, 0);
        return new ArrayList<>(hs);
    }

    public int dfs(List<List<Integer>> graph, int rank[], int index, HashSet<List<Integer>> connections, int depth){
        if(rank[index] != -2)
            return rank[index];
        rank[index] = depth;
        int minDepth = depth;

        for(int neighbour:graph.get(index)){
            if(rank[neighbour] == depth-1)
                continue;
            int temp = dfs(graph, rank, neighbour, connections, depth+1);
            minDepth = Math.min(minDepth, temp);
            if(temp <= depth){
                connections.remove(Arrays.asList(Math.min(neighbour, index), Math.max(neighbour, index)));
            }
        }
        return minDepth;
    }
}