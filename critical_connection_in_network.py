# Time Complexity : O(V+E)
# Space Complexity : O(V)
from typing import List
class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        hashMap = {}
        discovery = [-1]*n
        lowest = [0]*n
        result = []
        self.time = 0

        def buildGraph(connections):
            for edge in connections:
                n1 = edge[0]
                n2 = edge[1]
                if n1 not in hashMap:
                    hashMap[n1] = []
                if n2 not in hashMap:
                    hashMap[n2] = []
                hashMap[n1].append(n2)
                hashMap[n2].append(n1)
        
        def dfs(v, u):
            if(discovery[v] != -1):
                return
            discovery[v] = self.time
            lowest[v] = self.time
            self.time+=1
            for n in hashMap[v]:
                if n==u: 
                    continue
                dfs(n, v)
                if lowest[n] > discovery[v]:
                    result.append([n, v])
                lowest[v] = min(lowest[v], lowest[n])

        buildGraph(connections)
        dfs(0,0)
        return result