// TC = O(V + E)
// SC = O(V + E)
public class Solution {
    HashMap<Integer, List<Integer>> map;
    int[] discovery;
    int[] lowest;
    int time;
    List<List<Integer>> result;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.result = new ArrayList<>();
        this.map = new HashMap<>();
        this.lowest = new int[n];
        this.discovery = new int[n];
        Arrays.fill(discovery, -1);

        buildGraph(connections);
        dfs(0, 0);

        return result;
    }

    private void buildGraph(List<List<Integer>> connections) {
        for (List<Integer> edge : connections) {
            int n1 = edge.get(0);
            int n2 = edge.get(1);
            if (!map.containsKey(n1)) {
                map.put(n1, new ArrayList<>());
            }
            if (!map.containsKey(n2)) {
                map.put(n2, new ArrayList<>());
            }
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }
    }

    private void dfs(int curr, int parent) {
        int children = 0;
        discovery[curr] = lowest[curr] = ++time;
        
        for (int neighbor : map.get(curr)) {
            if (neighbor == parent) {
                continue;
            }
            if (discovery[neighbor] == -1) {
                children++;
                dfs(neighbor, curr);
                lowest[curr] = Math.min(lowest[curr], lowest[neighbor]);
                if (lowest[neighbor] > discovery[curr]) {
                    result.add(Arrays.asList(curr, neighbor));
                }
            } else {
                lowest[curr] = Math.min(lowest[curr], discovery[neighbor]);
            }
        }
    }
}
