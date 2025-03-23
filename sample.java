// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        int color=0;
        for (int i=0;i<n;i++) {
            if (colors[i] == -1) {
                dfs(graph, i, color, colors);
            }
            color++;
        }
        int[] total = new int[color];
        for (int k=0;k<n;k++) {
            int index = colors[k];
            total[index]++;
        }
        int[] infectedInGroups= new int[color];
        for (int l=0;l<initial.length;l++) {
            int index = colors[initial[l]];
            infectedInGroups[index]++;
        }
        int answer=-1;
        for (int m=0;m<initial.length;m++) {
            int group = colors[initial[m]];
            System.out.println("here");
            if(infectedInGroups[group] == 1) {
                System.out.println("here1");
                if(answer == -1) {
                    answer = initial[m];
                    System.out.println("here2");
                } else if(total[group] > total[colors[answer]]) {
                    answer = initial[m];
                    System.out.println("here3");
                } else if(total[group] == total[colors[answer]] && answer > initial[m]) {
                    answer = initial[m];
                    System.out.println("here4");
                }
            }
        }
        if (answer == -1) {
            int min = Integer.MAX_VALUE;
            for(int i=0;i<initial.length;i++) {
                min = Math.min(min, initial[i]);
                answer=min;
            }
        }
        return answer;
    }

    private void dfs(int[][] graph, int node, int color, int[] colors) {
        if (colors[node] != -1) {
            return;
        }
        colors[node] = color;
        for(int j=0;j<graph.length;j++) {
            if(graph[node][j] == 1) {
                dfs(graph, j, color, colors);
            }
        }
    }
}
