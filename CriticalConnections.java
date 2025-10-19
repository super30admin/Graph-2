import java.util.Arrays;

//We build a graph and then calculate discovery time and lowest time for each node
//if a neighbor's lowest time is greater than the current node's discovery time,it is a criticall edge

//Time Complexity:O(V+E)
//Space Complexity: O(V+E)
class Solution{
    int[] discovery;
    int[] lowest;
    HashMap<Integer,List<Integer>> map;
    List<List<Integer>> result;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections)
    {
        // Creating a Graph for traversal
        for(int i=0;i<n;i++)
        {
            map.put(i,new ArrayList<>());
        }
        for(List<Integer> connection: connections)
        {
            int u = connection.get(0);
            int v = connection.get(1);
            map.get(u).add(v);
            map.get(v).add(u);
        }

        
        int[] discovery = new int[n];
        int[] lowest = new int[n];

        Arrays.fill(discovery, -1);

        this.time = 0;
        dfs(0);
        return result;

    }

    private void dfs(int u, int parent)
    {
        if(discovery[u] !=-1) return;

        discovery[u] = time;
        lowest[u] = time;
        time++;
        //traverse through all the neiggbours
        for(int ne:map.get(u))
        {
            if(ne==parent) continue;
            dfs(ne,u);
            if(lowest[ne]> discovery[u])
            {
                result.add(Arrays.asList(ne,u));
            }
            lowest[u] = Math.min(lowest[u],lowest[ne]);
        }
    } 
}