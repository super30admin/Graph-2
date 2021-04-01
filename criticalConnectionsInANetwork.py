# Time Complexity : O(V+E)
# Space Complexity : O(E)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.graph = []
        self.res = []
        self.buildGraph(connections,n)
        self.time = 0 
        
        disc = [-1 for i in range(n)]
        low = [0 for i in range(n)]
        self.dfs(0,0, disc, low)
        return self.res
    
    def buildGraph(self, connections, n):
        for i in range(n):
            self.graph.append([])
        
        for edge in connections:
            to = edge[0]
            frm = edge[1]
            self.graph[to].append(frm)
            self.graph[frm].append(to)
            
    def dfs(self, v, u, discovery, low):
        if discovery[v] != -1:
            return
        
        discovery[v] = self.time
        low[v] = self.time
        self.time += 1
        neighbors = self.graph[v]
        for n in neighbors:
            if n == u:
                continue
            
            self.dfs(n, v, discovery, low)
            if low[n] > discovery[v]:
                self.res.append([n,v])
            low[v] = min(low[v], low[n])
            