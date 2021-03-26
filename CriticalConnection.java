//Time Complexity: o(v+e)
//Space Complexity: o(v+e)
//Expln: maintain a distance|discovery array and a latest array. Keep incrementing count and add it to to the array until we
// traverse through the nodes. If we hit a deadpoint check if the latest is greater that the distance of parent because it should not be
// then that is a critical connection. It should not be because we keep changing the latest of parent everytime we backtrack
// so we keep the latest low than distance of parent. In this way we will find out critical connection

class Solution {
    int count =0;
    List<List<Integer>> graph;
    List<List<Integer>> result;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        for(int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        result = new ArrayList<>();
        int [] distance = new int[n];
        int[] latest = new int[n];
        Arrays.fill(distance, -1);
        Arrays.fill(latest, -1);
        for(List<Integer> li : connections)
        {
            graph.get(li.get(0)).add(li.get(1));
            graph.get(li.get(1)).add(li.get(0));
        }

        dfs(0,0, distance, latest);

        return result;
    }
    private void dfs(int parent, int child, int[] distance, int[] latest)
    {
        //base
        if(distance[child] != -1) return;
        
        //logic
        distance[child] = count;
        latest[child] = count;
        count++;
        List<Integer> neighbors =  graph.get(child);
        for(Integer li: neighbors)
        {
            if(li == parent)
            {
                continue;
            }
            dfs(child, li, distance, latest);
            if(latest[li] > distance[child])
                result.add(Arrays.asList(li, child));
            
            latest[child] = Math.min(latest[li], latest[child]);
        }
        
    }
}