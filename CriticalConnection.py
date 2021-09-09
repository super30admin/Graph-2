class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        if connections is None or len(connections)==0:
            return 0
        self.result = []
        self.lowest = [-1]*n
        self.discovery = [-1]*n
        self.graph={}
        self.time=0
        for u,v in connections:
            self.graph.setdefault(u, []).append(v)
            self.graph.setdefault(v, []).append(u)
        self.dfs(0,0,self.lowest,self.discovery)
        return self.result
    
    def dfs(self,v,u,lowest,discovery):
        if self.discovery[v]!=-1:
            return
        self.lowest[v]=self.time
        self.discovery[v]=self.time
        self.time+=1
        neighbours = self.graph.get(v)
        for neighbour in neighbours:
            if neighbour == u:
                continue
            self.dfs(neighbour,v,lowest,discovery)
            if self.lowest[neighbour]>self.discovery[v]:
                self.result.append([neighbour,v])
            self.lowest[v]=min(self.lowest[v],self.lowest[neighbour])
            
        
    