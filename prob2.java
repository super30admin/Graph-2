// Time Complexity : O(n2+m2)
// Space Complexity : O(n+m)
// Did this code successfully run on Leetcode : Yes


class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int N = graph.length;
        DSU dsu = new DSU(N);

        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                if(graph[i][j]==1 || graph[j][i]==1)
                    dsu.union(i,j);
            }
        }

        int M = initial.length; 
        DSU initialNodesDSU = new DSU(M);
        for(int i=0;i<M;i++){
            for(int j=i+1;j<M;j++){
                if(dsu.find(initial[i])==dsu.find(initial[j])){
                    initialNodesDSU.union(i,j);
                }
            }
        }

        int nodeToBeRemoved = -1;
        int nodeAffectedCount =  -1;

        for(int i=0;i<M;i++){
            int currentNode = initial[i]; 
            if(initialNodesDSU.size(i)==1){
                int currentNodeAffectedCount = dsu.size(currentNode);
                if(currentNodeAffectedCount > nodeAffectedCount){
                    nodeToBeRemoved = currentNode;
                    nodeAffectedCount = currentNodeAffectedCount;
                }else if(currentNodeAffectedCount == nodeAffectedCount && currentNode < nodeToBeRemoved){
                    nodeToBeRemoved = currentNode;
                } 
            }
        }

        if(nodeToBeRemoved== -1){
            nodeToBeRemoved = N-1;
            for(int node : initial){
                nodeToBeRemoved = Math.min(nodeToBeRemoved, node);
            }
        }

        return nodeToBeRemoved;
    }

    class DSU{
        int[] parent ; 
        int[] size; 
        public DSU(int n){
            parent = new int[n+1];
            for(int i=0;i<=n;i++)
                parent[i] = i;
            size = new int[n+1]; 
            Arrays.fill(size, 1);   
        }

        int find(int x){
            while(x!=parent[x]){
                x = find(parent[x]);
            }
            return x;
        }

        void union(int x, int y){
            x = find(x);
            y = find(y);
            if(x==y) return; 
            if(size[x]<=size[y]){
                parent[x] = y;
                size[y]+=size[x];
            }else{
                parent[y] = x;
                size[x]+=size[y];
            }
        }

        int size(int x){
            return size[find(x)];
        }
    }
}

/*
 0 1 2 3 4 5
[1,0,0,0,0,0], 0
[0,1,1,0,0,0], 1
[0,1,1,0,0,0], 2
[0,0,0,1,1,1], 3
[0,0,0,1,1,1], 4
[0,0,0,1,1,1], 5

[2,3]

answer 3

1-2

3-4-5-3


[1,0,0],
[0,1,0],
[0,0,1]]

0 1 2

0 2

*/


