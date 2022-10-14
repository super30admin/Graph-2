// Time Complexity : O(V+E)
// Space Complexity : O(n)

//cycle detection algorithm (targan's algorithm)

class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int[] discovery;
    int[] lowest;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        result = new ArrayList<>();
        discovery = new int[n]; // natural order of discovery of any server
        lowest = new int[n]; // earliest discover node that we can hit from particular node.
        time = 0;
        Arrays.fill(discovery, -1);
        buildGraph(connections, n);
        dfs(0,0);
        return result;
    }

    //getting all the severs who has connection with the paricular server putting all in to an list
    private void buildGraph(List<List<Integer>> connections, int n){
        for(int i=0; i<n; i++){
            graph.add(new ArrayList<>());
        }

        for(List<Integer> c : connections){
            int n1 = c.get(0);
            int n2 = c.get(1);
            //putting both ways because it's undirected Connection
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
    }

    private void dfs(int v, int u){
        if(discovery[v] != -1) return;
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for(int n: graph.get(v)){
            if(n==u) continue;
            dfs(n,v);
            if(lowest[n]>discovery[v]){
                //getting critical connection
                result.add(Arrays.asList(n,v));
            }
            //updating lowest, to check whether it's in cycle or not
            lowest[v] = Math.min(lowest[v], lowest[n]);
        }
    }
}