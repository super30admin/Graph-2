/*
Problem: https://leetcode.com/problems/graph-connectivity-with-threshold/
*/


// Approach 1: TLE - because if we have a query for 2-6 and then 4-6 but we have traversed 4-6 while traversing 2-6, we're doing extra BFS traversals
// TC: O(n^2) + q * (v + e)
class Solution {
    HashMap<Integer, List<Integer>> graph;
    
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        buildGraph(n, threshold);
        return areCitiesConnected(n, queries);
    }
    
    private void buildGraph(int n, int g) {
        graph = new HashMap<>();
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i == j) continue;
                if (gcd(i, j) > g) {
                    if (!graph.containsKey(i)) {
                        graph.put(i, new ArrayList<>());
                    }
                    graph.get(i).add(j);
                }
            }
        }
    }
    
    private List<Boolean> areCitiesConnected(int n, int queries[][]) {
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < queries.length; ++i)
            result.add(false);
        
        for (int i = 0; i < queries.length; ++i) {
            int src = queries[i][0];
            int dest = queries[i][1];
            Queue<Integer> queue = new LinkedList<>();
            boolean visited[] = new boolean[n + 1];
            queue.add(src);
            visited[src] = true;
            
            while (!queue.isEmpty()) {
                int curCity = queue.poll();
                
                if (curCity == dest) {
                    result.set(i, true);
                    break;
                }
                
                List<Integer> destCities = graph.get(curCity);
                if (destCities != null) {
                    for (int destCity : destCities) {
                        if (!visited[destCity]) {
                            visited[destCity] = false;
                            queue.add(destCity);
                        }
                    }
                }
            }
        }
        
        return result;
    }
    
    private int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
}

// Approahc 2: Optimize using union find - https://leetcode.com/problems/graph-connectivity-with-threshold/discuss/899462/Extremely-useful-Union-Find-class-Feel-Free-to-Reuse
