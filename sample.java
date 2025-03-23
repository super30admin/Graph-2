// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class sample{
    /*******************PROBLEM-2******************/
    //TC:0(N*N)
//SC:0(N)
    class Solution {
        int[] colours;
        int colour;
        public int minMalwareSpread(int[][] graph, int[] initial) {
            if(graph==null || graph.length==0){
                return 0;
            }
            int n=graph.length;
            colours=new int[n];
            colour=0;
            Arrays.fill(colours,-1);

            for(int i=0;i<n;i++){
                if(colours[i]==-1){
                    dfs(graph,i);
                    colour++;
                }
            }
            //find total nodes in each group
            int[] groups=new int[colour];
            for(int i=0;i<colours.length;i++){
                int index=colours[i];
                groups[index]++;
            }

            //calculate totalInfected nodes in each group
            int[] totalInfectedNodes=new int[colour];
            for(int i=0;i<initial.length;i++){
                int group=colours[initial[i]];
                totalInfectedNodes[group]++;
            }

            //iterate over the initial and find group with max nodes and infected node as 1
            int answer=-1;
            for(int i=0;i<initial.length;i++){
                int group=colours[initial[i]];
                int infNodesInGroup=totalInfectedNodes[group];
                if(infNodesInGroup==1){
                    if(answer==-1){
                        answer=initial[i];
                    }else if(groups[group]>groups[colours[answer]]){
                        answer=initial[i];
                    }else if(groups[group]==groups[colours[answer]] && answer>initial[i]){
                        answer=initial[i];
                    }
                }
            }

            if(answer==-1){
                int min=Integer.MAX_VALUE;
                for(int i=0;i<initial.length;i++){
                    min=Math.min(min,initial[i]);
                }
                answer=min;
            }
            return answer;

        }

        private void dfs(int[][] graph,int k){
            if(colours[k]!=-1){
                return;
            }
            colours[k]=colour;
            for(int i=0;i<graph.length;i++){
                if(graph[k][i]==1){
                    dfs(graph,i);
                }
            }
        }
    }
}
