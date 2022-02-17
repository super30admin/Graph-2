#time o(v+e),space o(v+e)
class Solution:
    def __init__(self):
        self.hmap={}
        self.discovery=[]
        self.lowest=[]
        self.time=0
        self.result=[]
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.discovery=[-1 for i in range(n)]
        self.lowest=[0 for i in range(n)]
        for i in connections:
            if i[0] not in self.hmap.keys():
                self.hmap[i[0]]=[]
            self.hmap[i[0]].append(i[1])
            if i[1] not in self.hmap.keys():
                self.hmap[i[1]]=[]
            self.hmap[i[1]].append(i[0])
       
        self.dfs(0,0)
        return self.result
    def dfs(self,v,u):
        #base
        
        if self.discovery[v]!=-1:
            return
        #logic
        self.discovery[v]=self.time
        self.lowest[v]=self.time
        self.time+=1
      
        for child in self.hmap[v]:
            if child==u:
                continue
            self.dfs(child,v)
            if self.lowest[child]>self.discovery[v]:
                self.result.append([child,v])
            self.lowest[v]=min(self.lowest[child],self.lowest[v])            
            
 
        
            
                
                
        