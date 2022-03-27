'''

TC: O(V+E)
SC: O(V)

'''
class Solution:
    def __init__(self):
        self.time = 0
        
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        if not connections:
            return []
        
        disc = [-1 for i in range(n)]
        low = [0 for i in range(n)]
        graph = {i : list() for i in range(n)}
        res = list()
        
        for con in connections:
            graph[con[0]].append(con[1])
            graph[con[1]].append(con[0])
        
        def dfs(node, par, graph, disc, low, res):
            if disc[node] != -1:
                return
            disc[node] = self.time
            low[node] = self.time
            self.time += 1
            
            for child in graph[node]:
                if child == par:
                    continue
                    
                dfs(child, node, graph, disc, low, res)
                                
                if low[child] > disc[node]:
                    res.append([node, child])
                
                low[node] = min(low[node], low[child])
            
        
        dfs(0, 0, graph, disc, low, res)
        
        return res
                        
                    
                
                
                
        
        
        
        
        