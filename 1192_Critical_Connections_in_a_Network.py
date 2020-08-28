# Time Complexity : O(V+E) [V = number of vertices, E = number of edges]
# Space Complexity : O(V+E) [V = number of vertices, E = number of edges]
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
from collections import defaultdict
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        
        def dfs(u,v):
            nonlocal time, discovery, lower, result
            
            ## base
            if discovery[v] != -1:
                return
            
            ## body
            discovery[v] = time
            lower[v] = time
            
            time+=1
            
            neighbours = graph[v]
            for node in neighbours:
                if u == node:
                    continue
                dfs(v, node)
                
                # check if the edge is critical
                if discovery[v] < lower[node]:
                    result.append([node, v])
                
                lower[v] = min(lower[v], lower[node])
        
        ## build graph
        graph = defaultdict(list)
        
        for v1, v2 in connections:
            graph[v1].append(v2)
            graph[v2].append(v1)
            
        time = 0
        
        result = []
        
        discovery = [-1 for _ in range(n)]
        
        lower = [0 for _ in range(n)]
        
        dfs(0,0)
        
        return result