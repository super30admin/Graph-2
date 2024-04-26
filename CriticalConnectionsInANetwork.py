'''
TC: O(V+E) - using forward looking DFS and iterating once over the graph
            complexity is the number of edges to the number of vertices
SC: O(E+V) - array for nodes (discovery and lowest) and dfs stack space
'''
import collections
from typing import List

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.gcounter = 0
        res = []
        discovery = []
        lowest = []

        def dfs(root, parent):
            if discovery[root] == -1 and lowest[root] == -1:
                discovery[root] = self.gcounter
                lowest[root] = self.gcounter
            else:
                return
            self.gcounter += 1
            for child in g[root]:
                if child == parent: 
                    continue
                dfs(child, root)
                if (lowest[child]>discovery[root]):
                    res.append([root,child])
                else:
                    lowest[root] = min(lowest[root], lowest[child])

        g = collections.defaultdict(list)
        for a,b in connections:
            g[a].append(b)
            g[b].append(a)
        
        for node in range(n):
            discovery.append(-1)
            lowest.append(-1)
        
        dfs(0, None)

        return res
s = Solution()
print(s.criticalConnections(4,[[0,1],[1,2],[2,0],[1,3]]))
print(s.criticalConnections(2,[[0,1]]))