//Time Complexity: O(n^2)
//Space Complexity: O(n)
//Code run successfully on LeetCode.

public class Problem2 {

    public int minMalwareSpread(int[][] graph, int[] initial) {
        
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors,-1);
        int color = 0;
        
        for(int i =0; i <n; i++)
        {
            if(colors[i] == -1)
            {
                dfs(graph,i,colors,color);
                color++;
            }   
        }
        
        for(int i =0; i <n; i++)
            System.out.print(colors[i]);
        
        int[] groups = new int[color];
        
        for(int i =0; i <n; i++)
        {
            if(colors[i] != -1)
                groups[colors[i]]++;
        }
        
        int[] infected = new int[color];
        
        for(int i =0; i < initial.length; i++)
        {
            infected[colors[initial[i]]]++;
        }
        
        int answer = Integer.MAX_VALUE;
        //int max = Integer.MIN_VALUE;
        
        for(int i =0; i < initial.length; i++)
        {
            int col = colors[initial[i]];
            int inf = infected[col];
            
            System.out.println(col);
            
            if(inf == 1)
            {
                if(answer == Integer.MAX_VALUE)
                {
                    answer = initial[i];
                }
                
                else if(groups[colors[initial[i]]] > groups[colors[answer]])
                    answer = initial[i];
                
                else if(groups[colors[initial[i]]] == groups[colors[answer]] && answer > initial[i])
                    answer = initial[i];
            }
        }
        
        if(answer == Integer.MAX_VALUE)
        {
            int min = Integer.MAX_VALUE;
            for(int i =0; i < initial.length; i++)
                min = Math.min(min, initial[i]);
            
            answer = min;
        }
        return answer;
    }
    
    private void dfs(int[][]graph, int v, int[] colors, int color)
    {
        if(colors[v] != -1)
            return;
        
        colors[v] = color;
        
        for(int i =0; i < graph[v].length; i++)
        {
            if(graph[v][i] == 1)
                dfs(graph,i,colors,color);
        }
    }
}
