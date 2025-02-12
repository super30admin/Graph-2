# Problem 1: Critical Connections in a Network
from typing import List

class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        result = []
        time = 0
        discovery = [-1] * n
        lowest = [-1] * n
        Map = {}

        for i in range(n):
            Map[i] = []

        for edge in connections:
            Map[edge[0]].append(edge[1])
            Map[edge[1]].append(edge[0])

        def dfs(V,U):
            nonlocal time
            #base
            if discovery[V] != -1: return

            #logic
            discovery[V] = time
            lowest[V] = time
            time += 1

            for ne in Map[V]:
                if ne == U: continue
                dfs(ne,V)
                if lowest[ne] > discovery[V]:
                    result.append([ne,V])
                lowest[V] = min(lowest[V],lowest[ne])


        dfs(0,-1)
        return result
        