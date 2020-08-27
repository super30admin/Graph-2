#Time Complexity - O(V+E)
#Space Complexity - O(V+E) 
from collections import defaultdict
class Solution(object):
    def criticalConnections(self, n, connections):
        """
        :type n: int
        :type connections: List[List[int]]
        :rtype: List[List[int]]
        """
        self.time = -1
        disc = [-1] * n
        parents = [-1] * n
        low = [-1] * n
        ans = []
        d = defaultdict(list)
        for i in connections:
            d[i[0]].append(i[1])
            d[i[1]].append(i[0])
        
        def dfs(u):
            #base
            if disc[u] != -1:
                return
            self.time = self.time + 1
            disc[u] = self.time
            low[u] = self.time
            
            for v in d[u]:
                if disc[v] == -1:
                    parents[v] = u
                    dfs(v)
                    if disc[u] < low[v]:
                        ans.append([u,v])
                    low[u] = min(low[u],low[v])
                elif v != parents[u]:
                    low[u] = min(low[u], disc[v])
        #print(d)
        for node in d:
            if disc[node] == -1:
                dfs(node)
        
        return ans