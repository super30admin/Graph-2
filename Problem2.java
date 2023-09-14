public class Problem2 {
    int[] colors;

    public int minMalwareSpread(int[][] graph, int[] initial) {
        if (graph == null)
            return -1;
        int n = graph.length;
        colors = new int[n];
        Arrays.fill(colors, -1);
        int color = 0;
        for (int i = 0; i < n; i++) {
            if (colors[i] == -1) {
                dfs(graph, color, i);
            }
            color++;
        }
        int[] groups = new int[color];
        int[] initialInfected = new int[color];

        for (int i = 0; i < n; i++) {
            int c = colors[i];
            groups[c]++;
        }

        for (int node : initial) {
            int c = colors[node];
            initialInfected[c]++;
        }
        int result = Integer.MAX_VALUE;

        for (int node : initial) {
            int col = colors[node];
            int countInfected = initialInfected[col];

            if (countInfected == 1) {
                if (result == Integer.MAX_VALUE) {
                    result = node;
                } else if (groups[colors[node]] > groups[colors[result]]) {
                    result = node;
                } else if (groups[colors[node]] == groups[colors[result]]) {
                    result = Math.min(node, result);
                }
            }
        }

        if (result == Integer.MAX_VALUE) {
            for (int node : initial)
                result = Math.min(result, node);
        }

        return result;
    }

    private void dfs(int[][] graph, int color, int nodeIndex) {
        if (colors[nodeIndex] != -1)
            return;

        colors[nodeIndex] = color;
        for (int i = 0; i < graph[nodeIndex].length; i++) {
            if (graph[nodeIndex][i] == 1) {
                dfs(graph, color, i);
            }
        }
    }
}