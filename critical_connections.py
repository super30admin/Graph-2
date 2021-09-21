
#Time Complexity: O(V+E)
#Space Complexity: O(V+E) 
from collections import defaultdict
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.result = []
        if len(connections)==0:
            return self.result
        
        adj_list = {}
        for i in range(0,n):
            adj_list[i] = []
        for edge in connections:
            adj_list[edge[0]].append(edge[1])
            adj_list[edge[1]].append(edge[0])
        
        self.discover = [-1]*n
        self.lowest = [0]*n
        self.time =0
        def dfs(v,u):
            #base
            
            if self.discover[v]!= -1:
                return
            #logic
            
            self.lowest[v] = self.time
            self.discover[v] = self.time
            
            self.time+=1
           
            for neighbors in adj_list[v]:
                if neighbors == u:continue
                dfs(neighbors,v)
                #critical connection
                if self.lowest[neighbors] > self.discover[v]:
                    self.result.append([neighbors,v])
                   
                #update irrespectively
                self.lowest[v] = min(self.lowest[v],self.lowest[neighbors])
                
        
        dfs(0,0)
        return self.result