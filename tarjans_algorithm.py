# // Time Complexity :O(v+e)
# // Space Complexity :O(v+e)
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no



#tarjans algorithm
#critical connections-connections which are not part of a cycle
class Solution:
    def __init__(self):
        self.graph=None
        self.result=[]
        self.discovery=None
        self.lowest=None
        self.time=0
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.graph=[[] for i in range(n)]
        self.discovery=[-1 for i in range(n)]
        self.lowest=[0 for i in range(n)]
        self.buildgraph(connections)
        self.dfs(0,0)
        return self.result
    def buildgraph(self,connections):
        for i in connections:
            n1=i[0]
            n2=i[1]
            self.graph[n1].append(n2)
            self.graph[n2].append(n1)
    def dfs(self,v,u):
        #base
        if self.discovery[v]!=-1:
            return
        #logic
        self.discovery[v]=self.time
        self.lowest[v]=self.time
        self.time+=1
        for child in self.graph[v]:
            if child == u:
                continue
            self.dfs(child,v)
            if self.lowest[child]>self.discovery[v]:
                self.result.append([child,v])
            self.lowest[v]=min(self.lowest[v],self.lowest[child])
            
            