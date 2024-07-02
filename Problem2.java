import java.util.Arrays;
// TC: O(n^2)
// SC: O(n)
public class Problem2 {
    int[] colorGroups;
    int n;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        this.n = graph.length;
        this.colorGroups = new int[n];
        Arrays.fill(colorGroups,-1);

        int clrGrp=0;
        for(int i=0;i<n;i++){
            if(colorGroups[i]==-1){
                dfs(graph,i,clrGrp);
                clrGrp++;
            }
        }
    
        int[] grpCount = new int[clrGrp];
        for(int group: colorGroups){
            grpCount[group]++;
        }

        int[] infGroups = new int[clrGrp];
        for(int infNode: initial){
            int group = colorGroups[infNode];
            infGroups[group]++;
        }

        int result = Integer.MAX_VALUE;
        for(int infNode: initial){
            int group = colorGroups[infNode];
            int infGrpCnt = infGroups[group];
            if(infGrpCnt == 1){
                if(result == Integer.MAX_VALUE){
                    result = infNode;
                } 
                else if(grpCount[colorGroups[infNode]]>grpCount[colorGroups[result]]){
                    result = infNode;
                } 
                else if(grpCount[colorGroups[infNode]]==grpCount[colorGroups[result]]){
                    result=Math.min(result,infNode);
                }
            }
        }
        if(result==Integer.MAX_VALUE){
            for(int infNode: initial){
                result=Math.min(result,infNode);
            }
        }
        return result;
    }

    private void dfs(int[][] graph,int i,int clrGrp){
        //base condition
        if(colorGroups[i] != -1){
            return;
        }
        //logic
        colorGroups[i]=clrGrp;
        for(int j=0;j<n;j++){
            if(i==j){
                continue;
            }
            if(graph[i][j]==1){
                dfs(graph,j,clrGrp);
            }
        }
    }
}
