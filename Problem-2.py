#Time Complexity :o(V+E)
#Space Complexity :o(V+E)
#Did this code successfully run on Leetcode :yes
#Any problem you faced while coding this :no

class Solution(object):
    def criticalConnections(self, n, connections):
        """
        :type n: int
        :type connections: List[List[int]]
        :rtype: List[List[int]]
        """
        self.graph=[]
        self.res=[]
        self.discovery=[-1]*n
        self.lowest=[0]*n
        self.time=0
        self.buildGrpah(connections,n)
        self.dfs(0,0)
        return self.res
    
    def dfs(self,v,u):
        #v-> is currNode
        #u->Parent
        #base
        if(self.discovery[v]!=-1): return
        #loiugc
        self.discovery[v]=self.time
        self.lowest[v]=self.time
        self.time+=1
        for n in self.graph[v]:
            if(n==u): continue
            self.dfs(n,v)
            if(self.lowest[n] > self.discovery[v]):
                self.res.append([n,v])
            self.lowest[v]=min(self.lowest[v],self.lowest[n])        
        
    def buildGrpah(self,connections,n):
        for i in range(n):
            self.graph.append([])
        for i in connections:
            to=i[0]
            frm=i[1]
            self.graph[to].append(frm)
            self.graph[frm].append(to)
            