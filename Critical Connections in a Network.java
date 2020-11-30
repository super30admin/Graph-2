//Time Complexity-O(V+E)
class Solution {
    List<List<Integer>>result;
    List<List<Integer>>graph;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result=new ArrayList();
        graph=new ArrayList();
        int[]discover=new int[n];
        int[]lower=new int[n];
        Arrays.fill(discover,-1);
        BuildGraph(n,connections);
        dfs(0,0,discover,lower,0);
        return result;
        
    }
    private void dfs(int current,int parent,int[]discover,int[]lower,int time)
    {
        if(discover[current]!=-1)
        {
            return;
        }
        discover[current]=time;
        lower[current]=time;
        time=time+1;
        List<Integer>neighbors=graph.get(current);
        for(Integer n:neighbors)
        {
            if(n==parent)
            {
                continue;
            }
            dfs(n,current,discover,lower,time);
            if(lower[n]>discover[current])
            {
                result.add(Arrays.asList(n,current));
            }
            else{
                lower[current]=Math.min(lower[n],lower[current]);
            }
        }
    }

    private void BuildGraph(int n,List<List<Integer>>connections)
    {
        for(int i=0;i<n;i++)
        {
            graph.add(new ArrayList());
        }
        for(List<Integer>list:connections)
        {
            int src=list.get(0);
            int des=list.get(1);
            graph.get(src).add(des);
            graph.get(des).add(src);
        }
    }

}