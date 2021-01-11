#The path in graph has critical connecttion, if there is cycle, when we find a cycle we can disconnect one of the routes.
#Time COmplexity: O(e+v)
#Sapce Complexity: O(v)
class Solution:
    def __init__(self):
        self.result = list()
        self.graph = list()
        self.time = 0
        
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.buildGraph(connections,n)
        discovery = [-1]*n
        lowest = [0]*n
        self.dfs(0,0,discovery,lowest)
        return self.result
    
    def buildGraph(self, connections , n):
        for i in range(n):
            self.graph.append(list())
        for edge in connections:
            to = edge[0]
            fro = edge[1]
            self.graph[to].append(fro)
            self.graph[fro].append(to)
    
    def dfs(self,v, u, discovery, lowest):
        if (discovery[v] != -1):
            return
        discovery[v] = self.time
        lowest[v] = self.time
        self.time += 1
        neighbors = self.graph[v]
        for n in neighbors:
            if n == u:
                continue
            self.dfs(u,v,discovery,lowest)
            if lowest[u] > discovery[v]:
                self.result.append(list(tuple(n,v)))
            lowest[v] = min(lowest[v], lowest[n])