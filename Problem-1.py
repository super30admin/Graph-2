"""
TC: O(V+E)
SC: O(n)
"""

from collections import defaultdict
class Solution:
    def __init__(self):
        self.graph = defaultdict(list)
        self.discovery = []
        self.lowest = []
        self.discovered = 0
        self.result = []
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        self.discovery = [-1] * n
        self.lowest = [0] * n
        self.build_graph(connections)
        self.dfs(0,0)
        return self.result
    
    
    def build_graph(self, connections):
        for node1, node2 in connections:
            self.graph[node1].append(node2)
            self.graph[node2].append(node1)
    
    def dfs(self, v, u): #child, parent
        if self.discovery[v] != -1: return
        self.discovery[v] = self.discovered
        self.lowest[v] = self.discovered
        self.discovered += 1
        for neighbor in self.graph[v]:
            if neighbor == u: continue
            self.dfs(neighbor, v)
            if self.lowest[neighbor] > self.discovery[v]:
                self.result.append([neighbor,v])
            self.lowest[v] = min(self.lowest[neighbor], self.lowest[v])
            