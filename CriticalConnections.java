// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class CriticalConnections {
    int[] discovery;
    int[] lowest;
    List<List<Integer>> res;
    HashMap<Integer, List<Integer>> map;
    int timeStamp;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.discovery = new int[n];
        this.lowest = new int[n];
        this.res = new ArrayList<>();
        this.map = new HashMap<>();
        this.timeStamp = 0;

        for(int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(List<Integer> con : connections) {
            map.get(con.get(0)).add(con.get(1));
            map.get(con.get(1)).add(con.get(0));
        }

        Arrays.fill(discovery, -1);
        dfs(0,-1);
        return res;
    }

    private void dfs(int curr, int parent) {
        //base case
        if(discovery[curr] != -1) return;

        //logic
        discovery[curr] = timeStamp;
        lowest[curr] = timeStamp;
        timeStamp++;
        for(int neigh : map.get(curr)) {
            if(neigh == parent) continue;
            dfs(neigh, curr);
            if(lowest[neigh] > discovery[curr]) {
                res.add(Arrays.asList(neigh, curr));
            }
            lowest[curr] = Math.min(lowest[neigh], lowest[curr]);
        }
    }
}