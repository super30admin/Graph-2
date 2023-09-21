//Problem 1 Critical Connections in a Network
// Time Complexity : O(n^2) //O(V+E)
// Space Complexity : O(n^2) //O(V+E)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// check in dfs manner all coverable nodes and check if there is a cycle, if yes then cycle edges surely doesnt make a critical edge,
// all non cycle edges are critical edges, (to put it in simple way)
class Solution {
    //O(V+E) O(V+E)
    HashMap<Integer, List<Integer>> map;
    int[] discovery;
    int[] lowest;
    List<List<Integer>> res;
    int timestamp;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        //discovery array maintains natural order of dfs
        //lowest array maintains earliest node of dfs order which can be reached from current node
        this.res=new ArrayList<>();
        this.map= new HashMap<>();
        this.discovery=new int[n];
        Arrays.fill(discovery,-1);
        this.lowest=new int[n];
        for(int i=0;i<n;i++){
            map.put(i, new ArrayList<>());
        }

        for(List<Integer> preq: connections){
            int first=preq.get(0);
            int second=preq.get(1);
            map.get(first).add(second);
            map.get(second).add(first);
        }

        dfs(0, 0);
        return res;
    }

    private void dfs(int v, int u){
        //base
        if(discovery[v]!=-1) return;

        //logic
        discovery[v]=timestamp;
        lowest[v]=timestamp;
        timestamp++;

        for(int baby: map.get(v)){
            if(baby==u) continue; // child is papa already
            dfs(baby, v);
            if(lowest[baby]>discovery[v]){
                res.add(Arrays.asList(baby,v));
            }
            lowest[v]=Math.min(lowest[baby], lowest[v]);
        }   
    }
}

//Problem 2 Minimize Malware Spread
// Time Complexity : O(n^2)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
//first check what nodes form a group(network) through DFS.(V+E) then check how many nodes are in certain network.
//then iterate through all malware nodes and check how many nodes can we save by removing them.
class Solution {
    //O(n^2) O(n)
    int[] colors;
    int n;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        this.n=graph.length;
        this.colors=new int[n];
        Arrays.fill(colors,-1); //O(n)
        int initialColor=0;

        //formation of colors array
        for(int i=0;i<n;i++){ //O(n)
            if(colors[i]==-1){
                dfs(graph, i, initialColor);
            }
            initialColor++;
        }
        //make groups array
        int [] groups=new int[initialColor];
        for(int color: colors){ //<<<<<O(n)
            groups[color]++;
        }

        int[] initGroups=new int[initialColor];
        for(int node: initial){ //<<<<<<O(n)
            int c=colors[node];
            initGroups[c]++;
        }
        int result=Integer.MAX_VALUE;
        for(int node: initial){ //<<<<<<O(n)
            int cl=colors[node];
            //initially in group cl, check how many nodes are infected.
            int cnt=initGroups[cl];

            if(cnt==1){
                if(result==Integer.MAX_VALUE){
                    result=node;
                }
                else if(groups[colors[result]]< groups[colors[node]]){
                    result=node;
                }
                else if((groups[colors[result]]==groups[colors[node]]) && node<result){
                    result=node;
                }
            }
        }//for

        if(result==Integer.MAX_VALUE){
            for(int node: initial) //<<<<<<O(n)
                result=Math.min(result, node);
        }
        return result;
    }

    private void dfs(int[][] graph, int idx, int initColor){
        //base
        if(colors[idx]!=-1) return;
        //logic
        colors[idx]=initColor;
        //iterate
        for(int j=0;j<n;j++){
            if(idx==j) continue;
            if(graph[idx][j]==1){
                dfs(graph, j, initColor);
            }
        }
    }
}