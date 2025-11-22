# Time Complexity : O(V+E) where V and E are the number of vertices and edges in the graph
# Space Complexity : O(V+E) where V and E are the number of vertices and edges in the graph
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am using DFS to traverse the graph and find critical connections (bridges).
# I maintain two arrays: discover to store the discovery time of each node,
# and lowest to store the lowest discovery time reachable from each node.
# I use a helper function to perform DFS, updating discovery and lowest values.
# If the lowest value of a neighbor is greater than the discovery time of the current node,
# it indicates a critical connection, which I add to the result list.   
# Finally, I return the list of critical connections.
from typing import List
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        discover = [-1] * n
        lowest = [0] * n
        maps = {i:[] for i in range(n)}
        self.time = 0
        res = []
        for u,v in connections:
            maps[u].append(v)
            maps[v].append(u)
        def helper(u,v):
            if discover[u] != -1:
                return 
            discover[u] = self.time
            lowest[u] = self.time
            self.time += 1
            for i in maps[u]:
                if i == v:
                    continue
                helper(i,u)
                if lowest[i] > discover[u]:
                    res.append([i,u])
                lowest[u] = min(lowest[u],lowest[i])
        helper(0,-1)
        return res
