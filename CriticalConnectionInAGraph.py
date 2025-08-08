# // Time Complexity : o(v+e)
# // Space Complexity : o(v+e)
# // Did this code successfully run on Leetcode : yes
# // Any problem you faced while coding this :
# no

# // Your code here along with comments explaining your approach
# We assign discovery and lowest time to each node using DFS.
# If a neighbor’s lowest time is greater than the current node's discovery time, it's a critical edge.

from typing import List
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.discovery = [-1]*n
        self.lowest = [0]*n
        self.res = []
        self.map = {i:[] for i in range(n)}
        # start the time
        self.time = 0
        # build graph
        for u,v in connections:
            self.map[u].append(v)
            self.map[v].append(u)
        # call helper or dfs for critical cycle or tarjans 
        self.helper(0,-1)
        return self.res
    
    def helper(self,u,v):
        # if already visited return
        # base
        if self.discovery[u]!=-1: return 
        # assign the discovery and lowest time
        self.discovery[u] = self.time
        self.lowest[u] = self.time
        self.time+=1
        # visit neighbors
        for ne in self.map[u]:
            # no backward dfs only forward
            if ne == v : continue
            # do the dfs from neighbor 
            self.helper(ne,u)
            # once it comes out check ne with parents lowers to find if its a cycle
            if self.lowest[ne] > self.discovery[u]:
                self.res.append([u,ne])
            # adjust the lowest of parent for future
            self.lowest[u] = min(self.lowest[u], self.lowest[ne])
            

        

        