// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach:
we are to find the number of critical edges in this problem.
so in this we use variables dtime which is the time taken to reach from root to that node and it might change when we traverse back in dfs.
Next is low variable --> to calculate the lowest distance from the root node.
In this problem to calculate the number of critical egdes we are trying to make all the edges in the connected graph to have the same low time.
The critical edge will always have low time of the children greater than the dtime of the parent.

# Time complexity --> o(V+E)
# space complexity --> o(E)
class Solution(object):
    def __init__(self):
        self.time=0
        # self.res=[]
    def dfs(self,adj,dtime,low,visited,u,parent,res):
        visited[u]=1
        self.time=self.time+1
        dtime[u]=self.time
        low[u]=self.time
        #doing dfs and setting dtime and low for each node in the graph.
        for v in adj[u]:
            if visited[v]==0:
                parent[v]=u
                self.dfs(adj,dtime,low,visited,v,parent,res)
                #when recursion unwinds then we make sure all the nodes in a connected graph are having the same low value.
                low[u]=min(low[u],low[v])
                #for a critical edge the low time of the children will be greater than the dtime of the parent.
                if low[v]>dtime[u]:
                    val=[]
                    val.append(u)
                    val.append(v)
                    res.append(val)
            #for every node we check if it is visited or not,If yes we check if the parent of that node is correctly stored in the array or not.If not we make sure that parent low time is changed as parent node can reach in a min distance to the children compared to the current distance of that node.
            elif v!=parent[u]:
                low[u]=min(low[u],dtime[v])

    def criticalConnections(self, n, connections):
        """
        :type n: int
        :type connections: List[List[int]]
        :rtype: List[List[int]]
        """
        if connections==None or len(connections)==0:
            return []
        # creating a adjacency list
        adj=[[] for i in range(n)]
        #creating a bidirectional adjacency list 
        for i in range(len(connections)):
                adj[connections[i][0]].append(connections[i][1])
                adj[connections[i][1]].append(connections[i][0])
        #making sure that the node is not traversed again using visited array
        visited=[0 for i in range(n)]
        #dtime is to calculate the time taken to reach the current node from the root node
        dtime=[0 for i in range(n)]
        #low is to store the lowest value required to lowest distance to the root node.
        low=[0 for i in range(n)]
        parent=[0 for i in range(n)]
        res=[]
        #for every node we traverse to check if any critical edges can be found or not.
        for i in range(n):
            self.dfs(adj,dtime,low,visited,i,parent,res)
        return res