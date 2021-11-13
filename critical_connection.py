
#Time Complexity : O(n)
#Space Complexity : O(n)
# Did this code successfully run on Leetcode : Yes
#Any problem you faced while coding this : No

class Solution:
    g = []
    res = []
    disc = []
    low = []
    t = 0
    def graph(self, connections):
        for con in connections:
            n1 = con[0]
            n2 = con[1]
            Solution.g[n1].append(n2)
            Solution.g[n2].append(n1)
            
    def dfs(self, v, u):
        #base
        if Solution.disc[v] != -1:
            return
        #logic
        Solution.disc[v] = Solution.t
        Solution.low[v] = Solution.t
        Solution.t += 1
        for child in Solution.g[v]:
            if child == u:
                continue
            self.dfs(child, v)
            if Solution.low[child] > Solution.disc[v]:
                Solution.res.append([child, v])
            Solution.low[v] = min(Solution.low[v], Solution.low[child])
                
        
            
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        Solution.g = [ [] for i in range(n)]
        Solution.res = []
        Solution.disc = [-1]*n
        Solution.low = [0]*n
        Solution.t = 0
        if len(connections) == 0:
            return res
        self.graph(connections)
        self.dfs(0, 0)
        return Solution.res