// TC: O(v + E)
// SC: O(V)
class Solution {

    List<List<Integer>> result;
    HashMap<Integer, List<Integer>> map;
    int[] discovery;
    int[] lowest;
    int time = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<List<Integer>>();
        map = new HashMap<>();
        discovery = new int[n];
        lowest = new int[n];
        Arrays.fill(discovery, -1);

        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<Integer>());
        }

        for (List<Integer> curr : connections) {
            int n1 = curr.get(0);
            int n2 = curr.get(1);
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }

        helper(0, 0);

        return result;
    }

    private void helper(int node, int parent) {
        // base
        if (discovery[node] != -1) {
            return;
        }
        // dfs
        discovery[node] = time;
        lowest[node] = time;
        time++;
        for (int ne : map.get(node)) {
            // ensures we dont look back
            if (ne == parent) {
                continue;
            }

            helper(ne, node);

            if (lowest[ne] > discovery[node]) {
                result.add(Arrays.asList(ne, node));
            }

            lowest[node] = Math.min(lowest[node], lowest[ne]);
        }

    }
}