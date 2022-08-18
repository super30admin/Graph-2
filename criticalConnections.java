class Solution {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] map = new ArrayList[n];
        Arrays.setAll(map, o -> new ArrayList<>());
        for (List<Integer> c : connections) {
            map[c.get(0)].add(c.get(1));
            map[c.get(1)].add(c.get(0));
        }
        int[] dp = new int[n];
        int[] lvl = new int[n];
        for (int i = 0; i < n; i++) {
            dfs(i, -1, map, dp, lvl);
        }
        return ans;
    }

    private void dfs(int cur, int parent, List<Integer>[] map, int[] dp, int[] lvl) {
        for (int next : map[cur]) {
            if (lvl[next] == 0) {
                lvl[next] = lvl[cur] + 1;
                dfs(next, cur, map, dp, lvl);
                dp[cur] += dp[next];
            } else if (lvl[next] < lvl[cur]) {
                dp[cur]++;
            } else if (lvl[next] > lvl[cur]) {
                dp[cur]--;
            }
        }
        if (--dp[cur] == 0 && parent != -1) {
            ans.add(List.of(cur, parent));
        }
    }
}