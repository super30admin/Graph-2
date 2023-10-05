# Trajan's algorithm
# Time Complexity : O(V+E)
# Space Complexity : O(V+E)
class Solution(object):
    def __init__(self):
        self.discovey=[]
        self.lowest=[]
        self.time=0
        self.hmap={}
        self.result=[]
    def criticalConnections(self, n, connections):
        """
        :type n: int
        :type connections: List[List[int]]
        :rtype: List[List[int]]
        """
        self.discovery=[-1 for _ in range(n)]
        self.lowest=[0 for _ in range(n)]
        self.buildGraph(connections,n)
        self.dfs(0,0)

        return self.result

    
    def buildGraph(self,connections,n):
        for i in range(n):
            self.hmap[i]=[]
        for edge in connections:
            first=edge[0]
            second=edge[1]
            self.hmap[first].append(second)
            self.hmap[second].append(first)

    def dfs(self,v,u):
        # base
        if self.discovery[v]!=-1:
            return
        # logic
        self.discovery[v]=self.time
        self.lowest[v]=self.time
        self.time+=1
        for ne in self.hmap[v]:
            if ne==u:
                continue
            self.dfs(ne,v)
            if self.lowest[ne]>self.discovery[v]:
                self.result.append([ne,v])
            self.lowest[v]=min(self.lowest[v],self.lowest[ne])